package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.model.CustomerMessage
import com.example.data.model.Order
import com.example.data.model.Product
import com.example.data.model.QuoteRequest
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentDark
import com.example.ui.theme.GoldAccentLight
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.WhatsAppGreen
import com.example.util.KenyanFormatters
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    products: List<Product>,
    orders: List<Order>,
    messages: List<CustomerMessage>,
    quoteRequests: List<QuoteRequest>,
    onAddProduct: (Product) -> Unit,
    onUpdateProduct: (Product) -> Unit,
    onDeleteProduct: (Product) -> Unit,
    onUpdateOrderStatus: (orderId: Long, status: String) -> Unit,
    onMarkMessageRead: (messageId: Long) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Products", "Orders", "Messages", "Overview")

    // State for Add / Edit Product Dialog
    var showProductDialog by remember { mutableStateOf(false) }
    var editingProduct by remember { mutableStateOf<Product?>(null) }

    val categories = listOf(
        "Food & Groceries", "Cooking Oil", "Flour & Cereals", "Sugar & Salt",
        "Tea & Spices", "Beverages", "Cleaning Products", "Personal Care",
        "Household Products", "Other Essentials"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("admin_screen")
    ) {
        // Admin Top Bar
        Surface(
            color = ForestGreenPrimary,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "Admin Dashboard",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Inua Jamii Megawholers Store",
                            fontSize = 11.sp,
                            color = GoldAccentLight
                        )
                    }
                }

                if (selectedTab == 0) {
                    Button(
                        onClick = {
                            editingProduct = null
                            showProductDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("admin_add_product_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Add Item", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Tab Row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = ForestGreenPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = (selectedTab == index),
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        // Tab Content
        when (selectedTab) {
            0 -> AdminProductsTab(
                products = products,
                onEdit = { p ->
                    editingProduct = p
                    showProductDialog = true
                },
                onDelete = onDeleteProduct,
                onToggleStock = { p ->
                    onUpdateProduct(p.copy(inStock = !p.inStock))
                }
            )
            1 -> AdminOrdersTab(
                orders = orders,
                onUpdateStatus = onUpdateOrderStatus
            )
            2 -> AdminMessagesTab(
                messages = messages,
                quoteRequests = quoteRequests,
                onMarkRead = onMarkMessageRead
            )
            3 -> AdminOverviewTab(
                products = products,
                orders = orders,
                messages = messages,
                quoteRequests = quoteRequests
            )
        }
    }

    // Add / Edit Product Dialog
    if (showProductDialog) {
        ProductEditDialog(
            initialProduct = editingProduct,
            categories = categories,
            onDismiss = { showProductDialog = false },
            onSave = { product ->
                if (editingProduct != null) {
                    onUpdateProduct(product)
                } else {
                    onAddProduct(product)
                }
                showProductDialog = false
            }
        )
    }
}

@Composable
private fun AdminProductsTab(
    products: List<Product>,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit,
    onToggleStock: (Product) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Manage Store Inventory (${products.size} Products)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Slate700
            )
        }

        items(products, key = { it.id }) { product ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth().testTag("admin_item_${product.id}")
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Thumbnail
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Slate100)
                        ) {
                            if (product.imageUrl.isNotBlank()) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(product.imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = product.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Default.Inventory2, contentDescription = null, tint = Slate400)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = product.name,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900,
                                maxLines = 1
                            )
                            Text(
                                text = "${product.category} • ${product.unit}",
                                fontSize = 11.sp,
                                color = Slate500
                            )

                            Row(
                                modifier = Modifier.padding(top = 2.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Retail: ${KenyanFormatters.formatKsh(product.retailPrice)}",
                                    fontSize = 11.sp,
                                    color = Slate700
                                )
                                Text(
                                    text = "Wholesale: ${KenyanFormatters.formatKsh(product.wholesalePrice)}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ForestGreenPrimary
                                )
                            }
                        }

                        // Actions
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { onEdit(product) }) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = ForestGreenPrimary, modifier = Modifier.size(20.dp))
                            }
                            IconButton(onClick = { onDelete(product) }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red, modifier = Modifier.size(20.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    HorizontalDivider(color = Slate200)
                    Spacer(modifier = Modifier.height(6.dp))

                    // Stock status and toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (product.inStock) "Status: In Stock (${product.stockQuantity} units)" else "Status: OUT OF STOCK",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (product.inStock) ForestGreenPrimary else Color.Red
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Available", fontSize = 11.sp, color = Slate600)
                            Spacer(modifier = Modifier.width(6.dp))
                            Switch(
                                checked = product.inStock,
                                onCheckedChange = { onToggleStock(product) },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = ForestGreenPrimary
                                ),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdminOrdersTab(
    orders: List<Order>,
    onUpdateStatus: (orderId: Long, status: String) -> Unit
) {
    val context = LocalContext.current
    val statuses = listOf("Pending", "Confirmed", "Processing", "Dispatched", "Delivered", "Cancelled")

    if (orders.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No customer orders recorded yet.", color = Slate500, fontSize = 14.sp)
        }
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Customer Orders (${orders.size})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Slate700
            )
        }

        items(orders, key = { it.id }) { order ->
            var showStatusDropdown by remember { mutableStateOf(false) }

            val statusColor = when (order.orderStatus) {
                "Delivered" -> ForestGreenPrimary
                "Dispatched" -> Color(0xFF2563EB)
                "Processing" -> GoldAccentDark
                "Cancelled" -> Color.Red
                else -> Color(0xFFEA580C)
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Top order number & date
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Order #${order.orderNumber}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Slate900
                        )

                        Surface(
                            color = statusColor.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = order.orderStatus.uppercase(),
                                color = statusColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    val dateStr = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(order.createdAt))
                    Text(text = "Placed: $dateStr • Type: ${order.orderType}", fontSize = 11.sp, color = Slate500)

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = Slate200)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Customer details
                    Text(text = "Customer: ${order.customerName}", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = Slate800)
                    Text(text = "Phone: ${order.customerPhone}", fontSize = 12.sp, color = Slate600)
                    Text(text = "Delivery To: ${order.town}, ${order.county} (${order.deliveryAddress})", fontSize = 12.sp, color = Slate600)

                    if (order.deliveryInstructions.isNotBlank()) {
                        Text(text = "Note: ${order.deliveryInstructions}", fontSize = 11.sp, color = GoldAccentDark)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Items summary
                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = order.itemsSummary,
                            fontSize = 11.sp,
                            color = Slate700,
                            lineHeight = 15.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Payment: ${order.paymentMethod}", fontSize = 11.sp, color = Slate500)
                            Text(
                                text = "Total: ${KenyanFormatters.formatKsh(order.totalAmount)}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = ForestGreenPrimary
                            )
                        }

                        // WhatsApp Contact & Change Status Button
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            OutlinedButton(
                                onClick = {
                                    val clientMsg = "Hello ${order.customerName}, this is Inua Jamii Megawholesalers Store regarding your order #${order.orderNumber}."
                                    KenyanFormatters.openWhatsAppChat(context, message = clientMsg, phone = order.customerPhone.replace("+", "").trim())
                                },
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.height(34.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = WhatsAppGreen, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("WhatsApp", fontSize = 11.sp, color = WhatsAppGreen)
                            }

                            Box {
                                Button(
                                    onClick = { showStatusDropdown = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.height(34.dp)
                                ) {
                                    Text("Status", fontSize = 11.sp)
                                }

                                DropdownMenu(
                                    expanded = showStatusDropdown,
                                    onDismissRequest = { showStatusDropdown = false }
                                ) {
                                    statuses.forEach { s ->
                                        DropdownMenuItem(
                                            text = { Text(s) },
                                            onClick = {
                                                onUpdateStatus(order.id, s)
                                                showStatusDropdown = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdminMessagesTab(
    messages: List<CustomerMessage>,
    quoteRequests: List<QuoteRequest>,
    onMarkRead: (Long) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // Bulk Quote Requests Section
        item {
            Text(
                text = "Bulk Wholesale Quote Requests (${quoteRequests.size})",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
        }

        if (quoteRequests.isEmpty()) {
            item {
                Text("No bulk quote requests submitted yet.", fontSize = 12.sp, color = Slate500)
            }
        } else {
            items(quoteRequests, key = { "quote_${it.id}" }) { quote ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = quote.businessName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = ForestGreenPrimary
                            )
                            Surface(color = GoldAccentLight, shape = RoundedCornerShape(4.dp)) {
                                Text(
                                    text = "${quote.estimatedUnits} units requested",
                                    fontSize = 10.sp,
                                    color = GoldAccentDark,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(text = "Contact: ${quote.contactPerson} • ${quote.phone} • ${quote.county}", fontSize = 12.sp, color = Slate600)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Products: ${quote.productsRequested}", fontSize = 12.sp, color = Slate800, fontWeight = FontWeight.Medium)
                        if (quote.additionalNotes.isNotBlank()) {
                            Text(text = "Notes: ${quote.additionalNotes}", fontSize = 11.sp, color = Slate500)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = {
                                    val msg = "Hello ${quote.contactPerson} of ${quote.businessName}, this is Inua Jamii Megawholesalers Store regarding your wholesale quotation request."
                                    KenyanFormatters.openWhatsAppChat(context, message = msg, phone = quote.phone.replace("+", "").trim())
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Reply on WhatsApp", fontSize = 11.sp)
                            }

                            OutlinedButton(
                                onClick = {
                                    KenyanFormatters.openDialer(context, quote.phone)
                                },
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Call", fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }

        // Contact Messages Section
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Customer Inquiries & Messages (${messages.size})",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
        }

        if (messages.isEmpty()) {
            item {
                Text("No contact messages received yet.", fontSize = 12.sp, color = Slate500)
            }
        } else {
            items(messages, key = { "msg_${it.id}" }) { msg ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = msg.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            if (msg.isRead) {
                                Text("Read", fontSize = 10.sp, color = Slate400)
                            } else {
                                Surface(color = ForestGreenContainer, shape = RoundedCornerShape(4.dp)) {
                                    Text("New", fontSize = 10.sp, color = ForestGreenPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                                }
                            }
                        }
                        Text(text = "Subject: ${msg.subject}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ForestGreenDark)
                        Text(text = "From: ${msg.phone} • ${msg.email}", fontSize = 11.sp, color = Slate500)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "\"${msg.message}\"", fontSize = 12.sp, color = Slate700, lineHeight = 16.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = {
                                    val rMsg = "Hello ${msg.name}, thank you for contacting Inua Jamii Megawholesalers Store regarding '${msg.subject}'."
                                    KenyanFormatters.openWhatsAppChat(context, message = rMsg, phone = msg.phone.replace("+", "").trim())
                                    onMarkRead(msg.id)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Text("Reply on WhatsApp", fontSize = 11.sp)
                            }

                            if (!msg.isRead) {
                                OutlinedButton(
                                    onClick = { onMarkRead(msg.id) },
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.height(32.dp)
                                ) {
                                    Text("Mark Read", fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdminOverviewTab(
    products: List<Product>,
    orders: List<Order>,
    messages: List<CustomerMessage>,
    quoteRequests: List<QuoteRequest>
) {
    val totalInventoryValue = products.sumOf { it.retailPrice * it.stockQuantity }
    val totalRevenueOrders = orders.filter { it.orderStatus != "Cancelled" }.sumOf { it.totalAmount }

    LazyColumn(
        contentPadding = PaddingValues(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Store Analytics & Performance",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MetricCard(
                    title = "Total Catalog",
                    value = "${products.size} Items",
                    subtitle = "10 Categories",
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Total Orders",
                    value = orders.size.toString(),
                    subtitle = "${orders.count { it.orderStatus == "Pending" }} Pending",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MetricCard(
                    title = "Estimated Inventory",
                    value = KenyanFormatters.formatKsh(totalInventoryValue),
                    subtitle = "Retail Valuation",
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Total Order Sales",
                    value = KenyanFormatters.formatKsh(totalRevenueOrders),
                    subtitle = "App Transactions",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MetricCard(
                    title = "Inquiries & Quotes",
                    value = "${messages.size + quoteRequests.size}",
                    subtitle = "${quoteRequests.size} Bulk Quotes",
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Stock Status",
                    value = "${products.count { it.inStock }} In Stock",
                    subtitle = "${products.count { !it.inStock }} Out of Stock",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Store Configuration & Contact Identity",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Store Name: Inua Jamii Megawholers Store", fontSize = 12.sp, color = Slate700)
                    Text("Customer Desk: +254783831157 (Phone & WhatsApp)", fontSize = 12.sp, color = Slate700)
                    Text("Official Email: megawholesalers12@gmail.com", fontSize = 12.sp, color = Slate700)
                    Text("Website: https://Inuajamiimegawholesalersstore/", fontSize = 12.sp, color = Slate700)
                    Text("Accepted Payments: M-Pesa on Delivery, Till, Cash on Delivery", fontSize = 12.sp, color = Slate700)
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    title: String,
    value: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = title, fontSize = 11.sp, color = Slate500, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Slate900)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = subtitle, fontSize = 10.sp, color = ForestGreenPrimary, fontWeight = FontWeight.SemiBold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductEditDialog(
    initialProduct: Product?,
    categories: List<String>,
    onDismiss: () -> Unit,
    onSave: (Product) -> Unit
) {
    var name by remember { mutableStateOf(initialProduct?.name ?: "") }
    var selectedCategory by remember { mutableStateOf(initialProduct?.category ?: categories.first()) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var retailPriceStr by remember { mutableStateOf(initialProduct?.retailPrice?.toInt()?.toString() ?: "") }
    var wholesalePriceStr by remember { mutableStateOf(initialProduct?.wholesalePrice?.toInt()?.toString() ?: "") }
    var wholesaleMinQtyStr by remember { mutableStateOf(initialProduct?.wholesaleMinQuantity?.toString() ?: "12") }
    var unit by remember { mutableStateOf(initialProduct?.unit ?: "Piece") }
    var stockQuantityStr by remember { mutableStateOf(initialProduct?.stockQuantity?.toString() ?: "100") }
    var inStock by remember { mutableStateOf(initialProduct?.inStock ?: true) }
    var description by remember { mutableStateOf(initialProduct?.description ?: "") }
    var imageUrl by remember { mutableStateOf(initialProduct?.imageUrl ?: "") }
    var badge by remember { mutableStateOf(initialProduct?.badge ?: "") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = if (initialProduct != null) "Edit Product" else "Add New Product",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (errorMessage != null) {
                    Text(errorMessage!!, color = Color.Red, fontSize = 11.sp, modifier = Modifier.padding(bottom = 6.dp))
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Product Name *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Category dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedCategory,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    selectedCategory = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = retailPriceStr,
                        onValueChange = { retailPriceStr = it },
                        label = { Text("Retail Price (KSh) *") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = wholesalePriceStr,
                        onValueChange = { wholesalePriceStr = it },
                        label = { Text("Wholesale (KSh) *") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = wholesaleMinQtyStr,
                        onValueChange = { wholesaleMinQtyStr = it },
                        label = { Text("Min Wholesale Qty") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = unit,
                        onValueChange = { unit = it },
                        label = { Text("Packaging Unit") },
                        placeholder = { Text("e.g. Bale, 5L, Pack") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = stockQuantityStr,
                        onValueChange = { stockQuantityStr = it },
                        label = { Text("Stock Qty") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = badge,
                        onValueChange = { badge = it },
                        label = { Text("Badge (Optional)") },
                        placeholder = { Text("e.g. Hot Deal") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("Product Image URL (Optional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description & Specifications") },
                    minLines = 2,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("In Stock Availability", fontSize = 13.sp, color = Slate800)
                    Switch(
                        checked = inStock,
                        onCheckedChange = { inStock = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = ForestGreenPrimary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val rPrice = retailPriceStr.toDoubleOrNull()
                            val wPrice = wholesalePriceStr.toDoubleOrNull()
                            val minQty = wholesaleMinQtyStr.toIntOrNull() ?: 12
                            val stock = stockQuantityStr.toIntOrNull() ?: 100

                            if (name.isBlank() || rPrice == null || wPrice == null) {
                                errorMessage = "Please enter product name, valid retail and wholesale price."
                            } else {
                                val updated = Product(
                                    id = initialProduct?.id ?: 0,
                                    name = name.trim(),
                                    category = selectedCategory,
                                    retailPrice = rPrice,
                                    wholesalePrice = wPrice,
                                    wholesaleMinQuantity = minQty,
                                    unit = unit.trim().ifEmpty { "Piece" },
                                    stockQuantity = stock,
                                    inStock = inStock,
                                    description = description.trim(),
                                    imageUrl = imageUrl.trim(),
                                    isFeatured = initialProduct?.isFeatured ?: true,
                                    badge = badge.trim().ifEmpty { null }
                                )
                                onSave(updated)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary)
                    ) {
                        Text("Save Product")
                    }
                }
            }
        }
    }
}
