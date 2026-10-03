package com.example.data.repository

import com.example.data.db.CustomerMessageDao
import com.example.data.db.DefaultProductsSeed
import com.example.data.db.OrderDao
import com.example.data.db.ProductDao
import com.example.data.db.QuoteRequestDao
import com.example.data.model.CustomerMessage
import com.example.data.model.Order
import com.example.data.model.Product
import com.example.data.model.QuoteRequest
import kotlinx.coroutines.flow.Flow

class StoreRepository(
    private val productDao: ProductDao,
    private val orderDao: OrderDao,
    private val customerMessageDao: CustomerMessageDao,
    private val quoteRequestDao: QuoteRequestDao
) {
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()
    val allOrders: Flow<List<Order>> = orderDao.getAllOrders()
    val allMessages: Flow<List<CustomerMessage>> = customerMessageDao.getAllMessages()
    val allQuoteRequests: Flow<List<QuoteRequest>> = quoteRequestDao.getAllQuoteRequests()

    suspend fun ensureProductsSeeded() {
        if (productDao.getProductCount() == 0) {
            productDao.insertAll(DefaultProductsSeed.initialProducts)
        }
    }

    suspend fun getProductById(id: Long): Product? = productDao.getProductById(id)

    suspend fun insertProduct(product: Product): Long = productDao.insertProduct(product)

    suspend fun updateProduct(product: Product) = productDao.updateProduct(product)

    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)

    suspend fun insertOrder(order: Order): Long = orderDao.insertOrder(order)

    suspend fun updateOrderStatus(orderId: Long, status: String) =
        orderDao.updateOrderStatus(orderId, status)

    suspend fun insertCustomerMessage(message: CustomerMessage): Long =
        customerMessageDao.insertMessage(message)

    suspend fun markMessageAsRead(id: Long) = customerMessageDao.markAsRead(id)

    suspend fun insertQuoteRequest(quoteRequest: QuoteRequest): Long =
        quoteRequestDao.insertQuoteRequest(quoteRequest)

    suspend fun updateQuoteStatus(id: Long, status: String) =
        quoteRequestDao.updateStatus(id, status)
}
