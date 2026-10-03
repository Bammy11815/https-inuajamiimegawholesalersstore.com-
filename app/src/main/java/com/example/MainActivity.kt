package com.example

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.StoreHeader
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.CartScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.CheckoutScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProductDetailDialog
import com.example.ui.screens.ShopScreen
import com.example.ui.screens.WholesaleScreen
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentDark
import com.example.ui.theme.GoldAccentLight
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900
import com.example.ui.viewmodel.StoreViewModel

enum class ViewMode {
    WEBSITE,
    NATIVE_APP
}

enum class StoreNavScreen(val title: String) {
    HOME("Home"),
    SHOP("Shop"),
    CATEGORIES("Categories"),
    WHOLESALE("Wholesale"),
    ABOUT("About"),
    CONTACT("Contact"),
    CART("Cart"),
    CHECKOUT("Checkout"),
    ADMIN("Admin")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                StoreApp()
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun StoreApp(viewModel: StoreViewModel = viewModel()) {
    val context = LocalContext.current
    var viewMode by remember { mutableStateOf(ViewMode.WEBSITE) }
    var currentScreen by remember { mutableStateOf(StoreNavScreen.HOME) }
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    // Collect Reactive State for Native Screen
    val allProducts by viewModel.allProducts.collectAsStateWithLifecycle()
    val filteredProducts by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val cartSubtotal by viewModel.cartSubtotal.collectAsStateWithLifecycle()
    val cartTotalItems by viewModel.cartTotalItems.collectAsStateWithLifecycle()
    val cartWholesaleSavings by viewModel.cartWholesaleSavings.collectAsStateWithLifecycle()

    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val onlyWholesale by viewModel.onlyWholesale.collectAsStateWithLifecycle()
    val sortOption by viewModel.sortOption.collectAsStateWithLifecycle()

    val selectedProduct by viewModel.selectedProduct.collectAsStateWithLifecycle()
    val checkoutSuccessOrder by viewModel.checkoutSuccessOrder.collectAsStateWithLifecycle()
    val contactSuccessMessage by viewModel.contactSubmissionMessage.collectAsStateWithLifecycle()
    val quoteSuccessMessage by viewModel.quoteSubmissionMessage.collectAsStateWithLifecycle()

    val orders by viewModel.allOrders.collectAsStateWithLifecycle()
    val messages by viewModel.allMessages.collectAsStateWithLifecycle()
    val quoteRequests by viewModel.allQuoteRequests.collectAsStateWithLifecycle()

    // Handle Back Button
    BackHandler(enabled = true) {
        if (viewMode == ViewMode.WEBSITE && webViewRef?.canGoBack() == true) {
            webViewRef?.goBack()
        } else if (viewMode == ViewMode.NATIVE_APP && currentScreen != StoreNavScreen.HOME) {
            if (currentScreen == StoreNavScreen.CHECKOUT) {
                currentScreen = StoreNavScreen.CART
            } else {
                currentScreen = StoreNavScreen.HOME
            }
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                // View Mode Switcher Header
                Surface(
                    color = ForestGreenDark,
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "🌐 Mode: ",
                                color = GoldAccentLight,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                modifier = Modifier
                                    .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(14.dp))
                                    .padding(2.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (viewMode == ViewMode.WEBSITE) ForestGreenPrimary else Color.Transparent,
                                    modifier = Modifier.clickable { viewMode = ViewMode.WEBSITE }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Language,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Website View",
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (viewMode == ViewMode.NATIVE_APP) ForestGreenPrimary else Color.Transparent,
                                    modifier = Modifier.clickable { viewMode = ViewMode.NATIVE_APP }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.PhoneAndroid,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Native App",
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        if (viewMode == ViewMode.WEBSITE) {
                            IconButton(
                                onClick = { webViewRef?.reload() },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Reload Website",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

                // If Native App mode is active, show the native StoreHeader
                if (viewMode == ViewMode.NATIVE_APP && currentScreen != StoreNavScreen.ADMIN && currentScreen != StoreNavScreen.CHECKOUT) {
                    StoreHeader(
                        cartCount = cartTotalItems,
                        onNavigateToCart = { currentScreen = StoreNavScreen.CART },
                        onNavigateToShop = { currentScreen = StoreNavScreen.SHOP },
                        onNavigateToAdmin = { currentScreen = StoreNavScreen.ADMIN },
                        onSearchClick = { currentScreen = StoreNavScreen.SHOP }
                    )
                }
            }
        },
        bottomBar = {
            if (viewMode == ViewMode.NATIVE_APP && currentScreen != StoreNavScreen.ADMIN && currentScreen != StoreNavScreen.CHECKOUT) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .testTag("main_bottom_nav")
                ) {
                    NavigationBarItem(
                        selected = (currentScreen == StoreNavScreen.HOME),
                        onClick = { currentScreen = StoreNavScreen.HOME },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == StoreNavScreen.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                                contentDescription = "Home"
                            )
                        },
                        label = { Text("Home", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ForestGreenDark,
                            selectedTextColor = ForestGreenPrimary,
                            indicatorColor = ForestGreenContainer,
                            unselectedIconColor = Slate500,
                            unselectedTextColor = Slate700
                        )
                    )

                    NavigationBarItem(
                        selected = (currentScreen == StoreNavScreen.SHOP),
                        onClick = { currentScreen = StoreNavScreen.SHOP },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == StoreNavScreen.SHOP) Icons.Filled.Storefront else Icons.Outlined.Storefront,
                                contentDescription = "Shop"
                            )
                        },
                        label = { Text("Shop", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ForestGreenDark,
                            selectedTextColor = ForestGreenPrimary,
                            indicatorColor = ForestGreenContainer,
                            unselectedIconColor = Slate500,
                            unselectedTextColor = Slate700
                        )
                    )

                    NavigationBarItem(
                        selected = (currentScreen == StoreNavScreen.CATEGORIES),
                        onClick = { currentScreen = StoreNavScreen.CATEGORIES },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == StoreNavScreen.CATEGORIES) Icons.Filled.Category else Icons.Outlined.Category,
                                contentDescription = "Categories"
                            )
                        },
                        label = { Text("Categories", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ForestGreenDark,
                            selectedTextColor = ForestGreenPrimary,
                            indicatorColor = ForestGreenContainer,
                            unselectedIconColor = Slate500,
                            unselectedTextColor = Slate700
                        )
                    )

                    NavigationBarItem(
                        selected = (currentScreen == StoreNavScreen.WHOLESALE),
                        onClick = { currentScreen = StoreNavScreen.WHOLESALE },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == StoreNavScreen.WHOLESALE) Icons.Filled.Percent else Icons.Outlined.Percent,
                                contentDescription = "Wholesale"
                            )
                        },
                        label = { Text("Wholesale", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ForestGreenDark,
                            selectedTextColor = ForestGreenPrimary,
                            indicatorColor = ForestGreenContainer,
                            unselectedIconColor = Slate500,
                            unselectedTextColor = Slate700
                        )
                    )

                    NavigationBarItem(
                        selected = (currentScreen == StoreNavScreen.CART),
                        onClick = { currentScreen = StoreNavScreen.CART },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (cartTotalItems > 0) {
                                        Badge(containerColor = GoldAccent) {
                                            Text(cartTotalItems.toString(), fontSize = 9.sp)
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (currentScreen == StoreNavScreen.CART) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                                    contentDescription = "Cart"
                                )
                            }
                        },
                        label = { Text("Cart", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ForestGreenDark,
                            selectedTextColor = ForestGreenPrimary,
                            indicatorColor = ForestGreenContainer,
                            unselectedIconColor = Slate500,
                            unselectedTextColor = Slate700
                        )
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (viewMode == ViewMode.WEBSITE) {
                // Full Responsive Kenyan E-Commerce Website View
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null)
                            setBackgroundColor(android.graphics.Color.WHITE)
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            settings.useWideViewPort = true
                            settings.loadWithOverviewMode = true
                            settings.cacheMode = WebSettings.LOAD_DEFAULT
                            settings.allowFileAccess = true

                            webChromeClient = WebChromeClient()
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): Boolean {
                                    val url = request?.url?.toString() ?: return false
                                    if (url.startsWith("tel:") ||
                                        url.startsWith("mailto:") ||
                                        url.contains("wa.me") ||
                                        url.contains("whatsapp.com") ||
                                        url.startsWith("whatsapp:")
                                    ) {
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                            ctx.startActivity(intent)
                                        } catch (e: Exception) {
                                            // Fallback
                                        }
                                        return true
                                    }
                                    return false
                                }
                            }
                            loadUrl("file:///android_asset/web/index.html")
                            webViewRef = this
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Native App View
                when (currentScreen) {
                    StoreNavScreen.HOME -> HomeScreen(
                        featuredProducts = allProducts.filter { it.isFeatured },
                        onProductClick = { product -> viewModel.selectProduct(product) },
                        onAddToCart = { product -> viewModel.addToCart(product, 1) },
                        onNavigateToShop = { currentScreen = StoreNavScreen.SHOP },
                        onNavigateToWholesale = { currentScreen = StoreNavScreen.WHOLESALE },
                        onNavigateToCategories = { currentScreen = StoreNavScreen.CATEGORIES },
                        onNavigateToAbout = { currentScreen = StoreNavScreen.ABOUT },
                        onNavigateToContact = { currentScreen = StoreNavScreen.CONTACT },
                        onSelectCategory = { category ->
                            viewModel.setSelectedCategory(category)
                        }
                    )

                    StoreNavScreen.SHOP -> ShopScreen(
                        products = filteredProducts,
                        searchQuery = searchQuery,
                        selectedCategory = selectedCategory,
                        onlyWholesale = onlyWholesale,
                        sortOption = sortOption,
                        onSearchChange = { viewModel.setSearchQuery(it) },
                        onCategorySelect = { viewModel.setSelectedCategory(it) },
                        onWholesaleToggle = { viewModel.setOnlyWholesale(it) },
                        onSortChange = { viewModel.setSortOption(it) },
                        onProductClick = { product -> viewModel.selectProduct(product) },
                        onAddToCart = { product -> viewModel.addToCart(product, 1) },
                        onNavigateToWholesale = { currentScreen = StoreNavScreen.WHOLESALE },
                        onNavigateToAbout = { currentScreen = StoreNavScreen.ABOUT },
                        onNavigateToContact = { currentScreen = StoreNavScreen.CONTACT }
                    )

                    StoreNavScreen.CATEGORIES -> CategoriesScreen(
                        products = allProducts,
                        onSelectCategory = { category ->
                            viewModel.setSelectedCategory(category)
                        },
                        onNavigateToShop = { currentScreen = StoreNavScreen.SHOP },
                        onNavigateToWholesale = { currentScreen = StoreNavScreen.WHOLESALE },
                        onNavigateToAbout = { currentScreen = StoreNavScreen.ABOUT },
                        onNavigateToContact = { currentScreen = StoreNavScreen.CONTACT }
                    )

                    StoreNavScreen.WHOLESALE -> WholesaleScreen(
                        wholesaleProducts = allProducts.filter { it.wholesalePrice < it.retailPrice },
                        quoteSubmissionSuccess = quoteSuccessMessage,
                        onSubmitQuote = { bName, cPerson, phone, email, county, units, products, notes ->
                            viewModel.submitQuoteRequest(bName, cPerson, phone, email, county, units, products, notes)
                        },
                        onClearSuccess = { viewModel.clearQuoteSubmissionMessage() },
                        onProductClick = { product -> viewModel.selectProduct(product) },
                        onAddToCart = { product -> viewModel.addToCart(product, 12) },
                        onNavigateToShop = { currentScreen = StoreNavScreen.SHOP },
                        onNavigateToAbout = { currentScreen = StoreNavScreen.ABOUT },
                        onNavigateToContact = { currentScreen = StoreNavScreen.CONTACT },
                        onCategoryClick = { category ->
                            viewModel.setSelectedCategory(category)
                            currentScreen = StoreNavScreen.SHOP
                        }
                    )

                    StoreNavScreen.ABOUT -> AboutScreen(
                        onNavigateToShop = { currentScreen = StoreNavScreen.SHOP },
                        onNavigateToWholesale = { currentScreen = StoreNavScreen.WHOLESALE },
                        onNavigateToContact = { currentScreen = StoreNavScreen.CONTACT },
                        onCategoryClick = { category ->
                            viewModel.setSelectedCategory(category)
                            currentScreen = StoreNavScreen.SHOP
                        }
                    )

                    StoreNavScreen.CONTACT -> ContactScreen(
                        contactSuccessMessage = contactSuccessMessage,
                        onSubmitContact = { name, phone, email, subject, message ->
                            viewModel.submitContactMessage(name, phone, email, subject, message)
                        },
                        onClearSuccess = { viewModel.clearContactSubmissionMessage() },
                        onNavigateToShop = { currentScreen = StoreNavScreen.SHOP },
                        onNavigateToWholesale = { currentScreen = StoreNavScreen.WHOLESALE },
                        onNavigateToAbout = { currentScreen = StoreNavScreen.ABOUT },
                        onCategoryClick = { category ->
                            viewModel.setSelectedCategory(category)
                            currentScreen = StoreNavScreen.SHOP
                        }
                    )

                    StoreNavScreen.CART -> CartScreen(
                        cartItems = cartItems,
                        subtotal = cartSubtotal,
                        wholesaleSavings = cartWholesaleSavings,
                        onUpdateQuantity = { id, qty -> viewModel.updateCartQuantity(id, qty) },
                        onRemoveItem = { id -> viewModel.removeFromCart(id) },
                        onClearCart = { viewModel.clearCart() },
                        onProceedToCheckout = { currentScreen = StoreNavScreen.CHECKOUT },
                        onContinueShopping = { currentScreen = StoreNavScreen.SHOP }
                    )

                    StoreNavScreen.CHECKOUT -> CheckoutScreen(
                        cartItems = cartItems,
                        subtotal = cartSubtotal,
                        confirmedOrder = checkoutSuccessOrder,
                        onBack = { currentScreen = StoreNavScreen.CART },
                        onPlaceOrder = { name, phone, email, county, town, address, instructions, method ->
                            viewModel.placeOrder(name, phone, email, county, town, address, instructions, method)
                        },
                        onDismissConfirmation = { viewModel.dismissCheckoutSuccess() },
                        onNavigateToHome = { currentScreen = StoreNavScreen.HOME }
                    )

                    StoreNavScreen.ADMIN -> AdminScreen(
                        products = allProducts,
                        orders = orders,
                        messages = messages,
                        quoteRequests = quoteRequests,
                        onAddProduct = { viewModel.addProduct(it) },
                        onUpdateProduct = { viewModel.updateProduct(it) },
                        onDeleteProduct = { viewModel.deleteProduct(it) },
                        onUpdateOrderStatus = { id, s -> viewModel.updateOrderStatus(id, s) },
                        onMarkMessageRead = { id -> viewModel.markMessageAsRead(id) },
                        onBack = { currentScreen = StoreNavScreen.HOME }
                    )
                }

                // Floating WhatsApp button in native mode
                if (currentScreen != StoreNavScreen.ADMIN && currentScreen != StoreNavScreen.CHECKOUT) {
                    FloatingWhatsAppButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 16.dp, bottom = 16.dp)
                    )
                }

                // Product Detail Dialog
                selectedProduct?.let { product ->
                    ProductDetailDialog(
                        product = product,
                        onDismiss = { viewModel.selectProduct(null) },
                        onAddToCart = { prod, quantity ->
                            viewModel.addToCart(prod, quantity)
                        }
                    )
                }
            }
        }
    }
}
