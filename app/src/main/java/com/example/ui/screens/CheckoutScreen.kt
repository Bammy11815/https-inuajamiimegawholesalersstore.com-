package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.CartItem
import com.example.data.model.Order
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenOnContainer
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentDark
import com.example.ui.theme.MPesaGreen
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.WhatsAppGreen
import com.example.util.KenyanFormatters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    cartItems: List<CartItem>,
    subtotal: Double,
    confirmedOrder: Order?,
    onBack: () -> Unit,
    onPlaceOrder: (
        name: String,
        phone: String,
        email: String,
        county: String,
        town: String,
        deliveryAddress: String,
        instructions: String,
        paymentMethod: String
    ) -> Unit,
    onDismissConfirmation: () -> Unit,
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val kenyanCounties = listOf(
        "Nairobi", "Mombasa", "Kiambu", "Nakuru", "Machakos", "Kisumu",
        "Uasin Gishu (Eldoret)", "Kajiado", "Kilifi", "Nyeri", "Meru", "Kakamega",
        "Kisii", "Kericho", "Bungoma", "Trans Nzoia (Kitale)", "Laikipia", "Other County"
    )

    // Form states
    var customerName by remember { mutableStateOf("") }
    var customerPhone by remember { mutableStateOf("") }
    var customerEmail by remember { mutableStateOf("") }
    var selectedCounty by remember { mutableStateOf(kenyanCounties[0]) }
    var countyExpanded by remember { mutableStateOf(false) }
    var town by remember { mutableStateOf("") }
    var deliveryAddress by remember { mutableStateOf("") }
    var deliveryInstructions by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("M-Pesa on Delivery") }
    var validationError by remember { mutableStateOf<String?>(null) }

    val deliveryFee = if (subtotal >= 10000.0) 0.0 else 350.0
    val total = subtotal + deliveryFee

    // Confirmation Dialog
    if (confirmedOrder != null) {
        Dialog(
            onDismissRequest = {
                onDismissConfirmation()
                onNavigateToHome()
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("order_confirmation_dialog")
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .background(ForestGreenContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Success",
                            tint = ForestGreenPrimary,
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Order Placed Successfully!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        color = Slate100,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = "Order ID: ${confirmedOrder.orderNumber}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = ForestGreenPrimary,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Text(
                        text = "Thank you, ${confirmedOrder.customerName}. Your order for ${KenyanFormatters.formatKsh(confirmedOrder.totalAmount)} has been registered. Our dispatch desk will contact you at ${confirmedOrder.customerPhone}.",
                        fontSize = 12.sp,
                        color = Slate600,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Instant WhatsApp Confirmation button
                    Button(
                        onClick = {
                            val whatsAppMsg = KenyanFormatters.buildOrderWhatsAppMessage(
                                orderNumber = confirmedOrder.orderNumber,
                                customerName = confirmedOrder.customerName,
                                customerPhone = confirmedOrder.customerPhone,
                                county = confirmedOrder.county,
                                town = confirmedOrder.town,
                                itemsSummary = confirmedOrder.itemsSummary,
                                totalAmount = confirmedOrder.totalAmount,
                                paymentMethod = confirmedOrder.paymentMethod
                            )
                            KenyanFormatters.openWhatsAppChat(context, message = whatsAppMsg)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .testTag("send_order_to_whatsapp_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Send Order to WhatsApp (+254783831157)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = {
                            onDismissConfirmation()
                            onNavigateToHome()
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().height(42.dp)
                    ) {
                        Text("Back to Store Home", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .testTag("checkout_screen")
    ) {
        // Top Back Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Slate700)
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Checkout & Delivery",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
            }
        }

        // Order Summary Accordion Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Order Summary (${cartItems.size} items)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    cartItems.take(3).forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${item.productName} x ${item.quantity}",
                                fontSize = 12.sp,
                                color = Slate700,
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = KenyanFormatters.formatKsh(item.lineTotal),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                        }
                    }

                    if (cartItems.size > 3) {
                        Text(
                            text = "+ ${cartItems.size - 3} more items",
                            fontSize = 11.sp,
                            color = Slate500,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = Slate200)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal", fontSize = 12.sp, color = Slate600)
                        Text(KenyanFormatters.formatKsh(subtotal), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Delivery Fee", fontSize = 12.sp, color = Slate600)
                        Text(
                            text = if (deliveryFee == 0.0) "FREE" else KenyanFormatters.formatKsh(deliveryFee),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (deliveryFee == 0.0) ForestGreenPrimary else Slate900
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total Amount", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = Slate900)
                        Text(KenyanFormatters.formatKsh(total), fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = ForestGreenPrimary)
                    }
                }
            }
        }

        // Customer Details Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "1. Customer Contact Details",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = customerName,
                        onValueChange = { customerName = it },
                        label = { Text("Full Name *") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_name_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = customerPhone,
                        onValueChange = { customerPhone = it },
                        label = { Text("Phone Number / M-Pesa Number *") },
                        placeholder = { Text("0712345678 or +254...") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_phone_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = customerEmail,
                        onValueChange = { customerEmail = it },
                        label = { Text("Email Address (Optional)") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Delivery Location Details Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "2. Delivery Location (Kenya)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // County Dropdown
                    ExposedDropdownMenuBox(
                        expanded = countyExpanded,
                        onExpandedChange = { countyExpanded = !countyExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedCounty,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("County *") },
                            leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = ForestGreenPrimary) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = countyExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth().testTag("checkout_county_dropdown")
                        )

                        ExposedDropdownMenu(
                            expanded = countyExpanded,
                            onDismissRequest = { countyExpanded = false }
                        ) {
                            kenyanCounties.forEach { c ->
                                DropdownMenuItem(
                                    text = { Text(c) },
                                    onClick = {
                                        selectedCounty = c
                                        countyExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = town,
                        onValueChange = { town = it },
                        label = { Text("Town / Estate / Area *") },
                        placeholder = { Text("e.g. Eastleigh, Westlands, Thika Town, Nyali") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_town_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = deliveryAddress,
                        onValueChange = { deliveryAddress = it },
                        label = { Text("Street / Building / Shop Name *") },
                        placeholder = { Text("e.g. Makutano Plaza Ground Floor Shop 4") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_address_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = deliveryInstructions,
                        onValueChange = { deliveryInstructions = it },
                        label = { Text("Delivery Instructions / Landmark") },
                        placeholder = { Text("e.g. Near Shell petrol station, deliver before 2pm") },
                        minLines = 2,
                        maxLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Payment Method Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "3. Payment Method",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val paymentMethods = listOf(
                        "M-Pesa on Delivery" to "Pay safely upon inspecting your package via M-Pesa",
                        "M-Pesa Till / Paybill" to "Instant Till payment to Inua Jamii Megawholesalers",
                        "Cash on Delivery" to "Cash payment upon receiving parcel (Nairobi area)"
                    )

                    paymentMethods.forEach { (method, desc) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { paymentMethod = method }
                                .padding(vertical = 6.dp)
                        ) {
                            RadioButton(
                                selected = (paymentMethod == method),
                                onClick = { paymentMethod = method },
                                colors = RadioButtonDefaults.colors(selectedColor = ForestGreenPrimary)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(text = method, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate900)
                                Text(text = desc, fontSize = 11.sp, color = Slate500)
                            }
                        }
                    }
                }
            }
        }

        // Error message if any
        if (validationError != null) {
            item {
                Surface(
                    color = Color(0xFFFEE2E2),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = validationError!!,
                        color = Color(0xFFB91C1C),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        // Place Order Action Button
        item {
            Button(
                onClick = {
                    if (customerName.isBlank() || customerPhone.isBlank() || town.isBlank() || deliveryAddress.isBlank()) {
                        validationError = "Please fill in your name, phone number, town, and delivery address."
                    } else {
                        validationError = null
                        onPlaceOrder(
                            customerName,
                            customerPhone,
                            customerEmail,
                            selectedCounty,
                            town,
                            deliveryAddress,
                            deliveryInstructions,
                            paymentMethod
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("submit_order_btn")
            ) {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Confirm & Place Order (${KenyanFormatters.formatKsh(total)})",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
