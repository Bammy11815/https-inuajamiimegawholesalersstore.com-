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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.model.CartItem
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenOnContainer
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
import com.example.ui.theme.Slate900
import com.example.ui.theme.WhatsAppGreen
import com.example.util.KenyanFormatters

@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    subtotal: Double,
    wholesaleSavings: Double,
    onUpdateQuantity: (productId: Long, newQuantity: Int) -> Unit,
    onRemoveItem: (productId: Long) -> Unit,
    onClearCart: () -> Unit,
    onProceedToCheckout: () -> Unit,
    onContinueShopping: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val deliveryFee = if (subtotal >= 10000.0) 0.0 else 350.0
    val total = subtotal + deliveryFee

    if (cartItems.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
                .testTag("empty_cart_view"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = Slate100,
                    modifier = Modifier.size(90.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.RemoveShoppingCart,
                            contentDescription = "Empty Cart",
                            tint = Slate400,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Your Cart is Empty",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Explore our food staples, cooking oils, flour, and household essentials to start saving with wholesale and retail pricing.",
                    fontSize = 13.sp,
                    color = Slate500,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onContinueShopping,
                    colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("empty_cart_shop_btn")
                ) {
                    Icon(imageVector = Icons.Default.ShoppingBag, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Start Shopping Now", fontWeight = FontWeight.Bold)
                }
            }
        }
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .testTag("cart_screen")
    ) {
        // Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Shopping Cart",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "${cartItems.size} items in cart",
                        fontSize = 12.sp,
                        color = Slate500
                    )
                }

                OutlinedButton(
                    onClick = onClearCart,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Text("Clear Cart", fontSize = 11.sp, color = Color.Red)
                }
            }
        }

        // Wholesale Savings Banner (if applicable)
        if (wholesaleSavings > 0) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = ForestGreenContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, ForestGreenLight),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = ForestGreenPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Wholesale Discount Applied!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = ForestGreenDark
                            )
                            Text(
                                text = "You are saving ${KenyanFormatters.formatKsh(wholesaleSavings)} across eligible 12+ items.",
                                fontSize = 11.sp,
                                color = ForestGreenOnContainer
                            )
                        }
                    }
                }
            }
        }

        // Cart Items List
        items(cartItems, key = { it.productId }) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("cart_item_${item.productId}")
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Thumbnail
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Slate100)
                        ) {
                            if (item.imageUrl.isNotBlank()) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(item.imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = item.productName,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Inventory2,
                                        contentDescription = null,
                                        tint = Slate400,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Product Name & Pricing Tier Info
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.productName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900,
                                maxLines = 2
                            )
                            Text(
                                text = "Unit: ${item.unit}",
                                fontSize = 11.sp,
                                color = Slate500
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Tier indicator chip
                            if (item.isWholesaleApplied) {
                                Surface(
                                    color = ForestGreenPrimary,
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "Wholesale Rate: ${KenyanFormatters.formatKsh(item.wholesalePrice)} / pc",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            } else {
                                Column {
                                    Text(
                                        text = "Retail Rate: ${KenyanFormatters.formatKsh(item.retailPrice)} / pc",
                                        fontSize = 11.sp,
                                        color = Slate700,
                                        fontWeight = FontWeight.Medium
                                    )
                                    if (item.unitsNeededForWholesale > 0) {
                                        Text(
                                            text = "Add ${item.unitsNeededForWholesale} more for wholesale price (${KenyanFormatters.formatKsh(item.wholesalePrice)})",
                                            fontSize = 10.sp,
                                            color = GoldAccentDark,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }

                        // Remove Button
                        IconButton(
                            onClick = { onRemoveItem(item.productId) },
                            modifier = Modifier.testTag("remove_item_${item.productId}")
                        ) {
                            Icon(
                                imageVector = Icons.Default.DeleteOutline,
                                contentDescription = "Remove",
                                tint = Slate400
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = Slate200)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Quantity +/- and Line Total
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Quantity Stepper
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Slate100, RoundedCornerShape(8.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            IconButton(
                                onClick = { onUpdateQuantity(item.productId, item.quantity - 1) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease", tint = Slate700, modifier = Modifier.size(16.dp))
                            }

                            Text(
                                text = item.quantity.toString(),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )

                            IconButton(
                                onClick = { onUpdateQuantity(item.productId, item.quantity + 1) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", tint = Slate700, modifier = Modifier.size(16.dp))
                            }
                        }

                        // Total Price for this item
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Item Total",
                                fontSize = 10.sp,
                                color = Slate500
                            )
                            Text(
                                text = KenyanFormatters.formatKsh(item.lineTotal),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = ForestGreenPrimary
                            )
                        }
                    }
                }
            }
        }

        // Order Summary Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Order Summary",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Subtotal", fontSize = 13.sp, color = Slate600)
                        Text(text = KenyanFormatters.formatKsh(subtotal), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate900)
                    }

                    if (wholesaleSavings > 0) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Wholesale Savings", fontSize = 13.sp, color = ForestGreenLight, fontWeight = FontWeight.SemiBold)
                            Text(text = "- ${KenyanFormatters.formatKsh(wholesaleSavings)}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ForestGreenLight)
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Estimated Delivery", fontSize = 13.sp, color = Slate600)
                        Text(
                            text = if (deliveryFee == 0.0) "FREE (Bulk order)" else KenyanFormatters.formatKsh(deliveryFee),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (deliveryFee == 0.0) ForestGreenPrimary else Slate900
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Slate200)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Estimated Total", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Slate900)
                        Text(
                            text = KenyanFormatters.formatKsh(total),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = ForestGreenPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Proceed to checkout button
                    Button(
                        onClick = onProceedToCheckout,
                        colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("proceed_to_checkout_btn")
                    ) {
                        Text("Proceed to Checkout", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // WhatsApp Order All Cart Items Button
                    OutlinedButton(
                        onClick = {
                            val itemsList = StringBuilder()
                            cartItems.forEach {
                                val tier = if (it.isWholesaleApplied) "(Wholesale)" else "(Retail)"
                                itemsList.append("• ${it.productName} x ${it.quantity} ${it.unit} $tier @ ${KenyanFormatters.formatKsh(it.unitPrice)} = ${KenyanFormatters.formatKsh(it.lineTotal)}\n")
                            }
                            val orderMsg = """
                                Hello Inua Jamii Megawholers Store,
                                I would like to order my shopping cart:
                                ----------------------------
                                $itemsList
                                ----------------------------
                                Subtotal: ${KenyanFormatters.formatKsh(subtotal)}
                                Total: ${KenyanFormatters.formatKsh(total)}
                                
                                Please advise on payment (M-Pesa) and delivery schedule.
                            """.trimIndent()
                            KenyanFormatters.openWhatsAppChat(context, message = orderMsg)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = WhatsAppGreen),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(WhatsAppGreen)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("whatsapp_cart_order_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = WhatsAppGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Order Entire Cart via WhatsApp", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
