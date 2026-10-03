package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.CartItem
import com.example.data.model.CustomerMessage
import com.example.data.model.Order
import com.example.data.model.Product
import com.example.data.model.QuoteRequest
import com.example.data.repository.StoreRepository
import com.example.util.KenyanFormatters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class SortOption(val displayName: String) {
    POPULAR("Most Popular"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    NAME_A_Z("Name: A to Z")
}

class StoreViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StoreRepository

    init {
        val db = AppDatabase.getDatabase(application, viewModelScope)
        repository = StoreRepository(
            productDao = db.productDao(),
            orderDao = db.orderDao(),
            customerMessageDao = db.customerMessageDao(),
            quoteRequestDao = db.quoteRequestDao()
        )
        viewModelScope.launch {
            repository.ensureProductsSeeded()
        }
    }

    val allProducts: StateFlow<List<Product>> = repository.allProducts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allOrders: StateFlow<List<Order>> = repository.allOrders
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allMessages: StateFlow<List<CustomerMessage>> = repository.allMessages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allQuoteRequests: StateFlow<List<QuoteRequest>> = repository.allQuoteRequests
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Cart state
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    // Filters and search
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _onlyWholesale = MutableStateFlow(false)
    val onlyWholesale: StateFlow<Boolean> = _onlyWholesale.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.POPULAR)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    // Product detail modal state
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    // Checkout result
    private val _checkoutSuccessOrder = MutableStateFlow<Order?>(null)
    val checkoutSuccessOrder: StateFlow<Order?> = _checkoutSuccessOrder.asStateFlow()

    // Contact & Quote submission confirmation banners
    private val _contactSubmissionMessage = MutableStateFlow<String?>(null)
    val contactSubmissionMessage: StateFlow<String?> = _contactSubmissionMessage.asStateFlow()

    private val _quoteSubmissionMessage = MutableStateFlow<String?>(null)
    val quoteSubmissionMessage: StateFlow<String?> = _quoteSubmissionMessage.asStateFlow()

    // Filtered products flow
    val filteredProducts: StateFlow<List<Product>> = combine(
        allProducts,
        _searchQuery,
        _selectedCategory,
        _onlyWholesale,
        _sortOption
    ) { products, query, category, wholesaleOnly, sort ->
        var list = products

        if (category != "All") {
            list = list.filter { it.category.equals(category, ignoreCase = true) }
        }

        if (wholesaleOnly) {
            list = list.filter { it.wholesalePrice < it.retailPrice }
        }

        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            list = list.filter {
                it.name.lowercase().contains(q) ||
                        it.category.lowercase().contains(q) ||
                        it.description.lowercase().contains(q)
            }
        }

        when (sort) {
            SortOption.POPULAR -> list.sortedByDescending { if (it.isFeatured) 1 else 0 }
            SortOption.PRICE_LOW_TO_HIGH -> list.sortedBy { it.retailPrice }
            SortOption.PRICE_HIGH_TO_LOW -> list.sortedByDescending { it.retailPrice }
            SortOption.NAME_A_Z -> list.sortedBy { it.name }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Cart calculations
    val cartSubtotal: StateFlow<Double> = _cartItems.map { items ->
        items.sumOf { it.lineTotal }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val cartTotalItems: StateFlow<Int> = _cartItems.map { items ->
        items.sumOf { it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val cartWholesaleSavings: StateFlow<Double> = _cartItems.map { items ->
        items.sumOf { it.potentialWholesaleSavings }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }

    fun setOnlyWholesale(enabled: Boolean) {
        _onlyWholesale.value = enabled
    }

    fun setSortOption(option: SortOption) {
        _sortOption.value = option
    }

    fun selectProduct(product: Product?) {
        _selectedProduct.value = product
    }

    // Cart methods
    fun addToCart(product: Product, quantityToAdd: Int = 1) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.productId == product.id }
        if (index >= 0) {
            val existing = current[index]
            current[index] = existing.copy(quantity = existing.quantity + quantityToAdd)
        } else {
            current.add(
                CartItem(
                    productId = product.id,
                    productName = product.name,
                    category = product.category,
                    unit = product.unit,
                    quantity = quantityToAdd,
                    retailPrice = product.retailPrice,
                    wholesalePrice = product.wholesalePrice,
                    wholesaleMinQuantity = product.wholesaleMinQuantity,
                    imageUrl = product.imageUrl
                )
            )
        }
        _cartItems.value = current
    }

    fun updateCartQuantity(productId: Long, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(productId)
            return
        }
        val current = _cartItems.value.map { item ->
            if (item.productId == productId) item.copy(quantity = newQuantity) else item
        }
        _cartItems.value = current
    }

    fun removeFromCart(productId: Long) {
        _cartItems.value = _cartItems.value.filter { it.productId != productId }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    // Checkout & Order
    fun placeOrder(
        customerName: String,
        customerPhone: String,
        customerEmail: String,
        county: String,
        town: String,
        deliveryAddress: String,
        deliveryInstructions: String,
        paymentMethod: String
    ) {
        viewModelScope.launch {
            val items = _cartItems.value
            if (items.isEmpty()) return@launch

            val subtotal = items.sumOf { it.lineTotal }
            // Reasonable flat delivery estimation or free delivery for large wholesale
            val deliveryFee = if (subtotal >= 10000.0) 0.0 else 350.0
            val total = subtotal + deliveryFee

            val hasWholesale = items.any { it.isWholesaleApplied }
            val allWholesale = items.all { it.isWholesaleApplied }
            val orderType = when {
                allWholesale -> "Wholesale"
                hasWholesale -> "Mixed"
                else -> "Retail"
            }

            val summaryBuilder = StringBuilder()
            items.forEach { item ->
                val tier = if (item.isWholesaleApplied) "(Wholesale)" else "(Retail)"
                summaryBuilder.append("• ${item.productName} x ${item.quantity} ${item.unit} $tier @ ${KenyanFormatters.formatKsh(item.unitPrice)} = ${KenyanFormatters.formatKsh(item.lineTotal)}\n")
            }

            val orderNumber = KenyanFormatters.generateOrderNumber()
            val newOrder = Order(
                orderNumber = orderNumber,
                customerName = customerName,
                customerPhone = customerPhone,
                customerEmail = customerEmail,
                county = county,
                town = town,
                deliveryAddress = deliveryAddress,
                deliveryInstructions = deliveryInstructions,
                itemsSummary = summaryBuilder.toString().trim(),
                subtotal = subtotal,
                deliveryFee = deliveryFee,
                totalAmount = total,
                paymentMethod = paymentMethod,
                orderStatus = "Pending",
                orderType = orderType
            )

            val id = repository.insertOrder(newOrder)
            _checkoutSuccessOrder.value = newOrder.copy(id = id)
            clearCart()
        }
    }

    fun dismissCheckoutSuccess() {
        _checkoutSuccessOrder.value = null
    }

    // Contact messages
    fun submitContactMessage(
        name: String,
        phone: String,
        email: String,
        subject: String,
        message: String
    ) {
        viewModelScope.launch {
            val msg = CustomerMessage(
                name = name,
                phone = phone,
                email = email,
                subject = subject,
                message = message
            )
            repository.insertCustomerMessage(msg)
            _contactSubmissionMessage.value = "Thank you $name! Your message has been received. Our team will contact you shortly via $phone."
        }
    }

    fun clearContactSubmissionMessage() {
        _contactSubmissionMessage.value = null
    }

    // Quote requests
    fun submitQuoteRequest(
        businessName: String,
        contactPerson: String,
        phone: String,
        email: String,
        county: String,
        estimatedUnits: Int,
        productsRequested: String,
        additionalNotes: String
    ) {
        viewModelScope.launch {
            val quote = QuoteRequest(
                businessName = businessName,
                contactPerson = contactPerson,
                phone = phone,
                email = email,
                county = county,
                estimatedUnits = estimatedUnits,
                productsRequested = productsRequested,
                additionalNotes = additionalNotes
            )
            repository.insertQuoteRequest(quote)
            _quoteSubmissionMessage.value = "Wholesale quote request for '$businessName' has been registered. Our wholesale account manager will get back to you with custom bulk pricing."
        }
    }

    fun clearQuoteSubmissionMessage() {
        _quoteSubmissionMessage.value = null
    }

    // Admin Operations
    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun updateOrderStatus(orderId: Long, status: String) {
        viewModelScope.launch {
            repository.updateOrderStatus(orderId, status)
        }
    }

    fun markMessageAsRead(id: Long) {
        viewModelScope.launch {
            repository.markMessageAsRead(id)
        }
    }

    fun updateQuoteStatus(id: Long, status: String) {
        viewModelScope.launch {
            repository.updateQuoteStatus(id, status)
        }
    }
}
