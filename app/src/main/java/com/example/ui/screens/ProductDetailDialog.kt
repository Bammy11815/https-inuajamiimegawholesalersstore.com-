package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.model.Product
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
fun ProductDetailDialog(
    product: Product,
    onDismiss: () -> Unit,
    onAddToCart: (Product, Int) -> Unit
) {
    val context = LocalContext.current
    var quantity by remember { mutableIntStateOf(1) }

    val isWholesale = quantity >= product.wholesaleMinQuantity
    val unitPrice = if (isWholesale) product.wholesalePrice else product.retailPrice
    val totalPrice = unitPrice * quantity
    val totalSavings = if (isWholesale) (product.retailPrice - product.wholesalePrice) * quantity else 0.0

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .testTag("product_detail_dialog")
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Header Bar with close button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Product Details",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_detail_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Slate700
                        )
                    }
                }

                HorizontalDivider(color = Slate200)

                // Scrollable Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    // Product Image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(16.dp))
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
                                modifier = Modifier.fillMaxWidth().height(220.dp)
                            )
                        } else {
                            Box(
                                modifier = Modifier.fillMaxWidth().height(220.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Inventory2,
                                    contentDescription = null,
                                    tint = Slate400,
                                    modifier = Modifier.size(64.dp)
                                )
                            }
                        }

                        if (!product.badge.isNullOrBlank()) {
                            Surface(
                                color = GoldAccent,
                                shape = RoundedCornerShape(bottomEnd = 12.dp),
                                modifier = Modifier.align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.badge,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Category & Name
                    Text(
                        text = product.category,
                        color = ForestGreenLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.name,
                        color = Slate900,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Packaging Unit: ${product.unit} • In Stock: ${product.stockQuantity} units",
                        color = Slate500,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tier Pricing Breakdown Card
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isWholesale) ForestGreenContainer else Color(0xFFF8FAFC),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isWholesale) ForestGreenLight else Slate200
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Wholesale & Retail Tier Pricing",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = if (isWholesale) ForestGreenOnContainer else Slate900
                                )
                                if (isWholesale) {
                                    Surface(
                                        color = ForestGreenPrimary,
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(
                                            text = "WHOLESALE UNLOCKED",
                                            color = Color.White,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                // Retail Tier
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (!isWholesale) Color.White else Color.White.copy(alpha = 0.7f),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (!isWholesale) ForestGreenPrimary else Slate200
                                    ),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = "Retail (1-11 units)",
                                            fontSize = 11.sp,
                                            color = Slate600,
                                            fontWeight = FontWeight.Medium
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = KenyanFormatters.formatKsh(product.retailPrice),
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Slate900
                                        )
                                        Text(
                                            text = "per unit",
                                            fontSize = 10.sp,
                                            color = Slate500
                                        )
                                    }
                                }

                                // Wholesale Tier
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isWholesale) Color.White else Color.White.copy(alpha = 0.7f),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.5.dp,
                                        if (isWholesale) GoldAccent else Slate200
                                    ),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = "Wholesale (12+ units)",
                                            fontSize = 11.sp,
                                            color = GoldAccentDark,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = KenyanFormatters.formatKsh(product.wholesalePrice),
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = ForestGreenPrimary
                                        )
                                        Text(
                                            text = "Save ${KenyanFormatters.formatKsh(product.getSavingsPerUnit())} / unit",
                                            fontSize = 10.sp,
                                            color = ForestGreenLight,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quantity Selector
                    Text(
                        text = "Select Quantity (${product.unit}):",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .border(1.dp, Slate200, RoundedCornerShape(10.dp))
                                .background(Color.White, RoundedCornerShape(10.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            IconButton(
                                onClick = { if (quantity > 1) quantity-- },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Decrease",
                                    tint = Slate700
                                )
                            }

                            Text(
                                text = quantity.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900,
                                modifier = Modifier.padding(horizontal = 14.dp)
                            )

                            IconButton(
                                onClick = { quantity++ },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Increase",
                                    tint = Slate700
                                )
                            }
                        }

                        // Quick buttons to jump to wholesale
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            OutlinedButton(
                                onClick = { quantity = 1 },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.height(36.dp)
                            ) {
                                Text("1 pc", fontSize = 11.sp)
                            }
                            OutlinedButton(
                                onClick = { quantity = 12 },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = if (quantity == 12) ForestGreenPrimary else Slate700
                                ),
                                modifier = Modifier.height(36.dp)
                            ) {
                                Text("12 pcs (Wholesale)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            OutlinedButton(
                                onClick = { quantity = 24 },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.height(36.dp)
                            ) {
                                Text("24 pcs", fontSize = 11.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Live Total Calculation
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF1F5F9),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Total Price ($quantity units):",
                                    fontSize = 12.sp,
                                    color = Slate600
                                )
                                if (totalSavings > 0) {
                                    Text(
                                        text = "You save ${KenyanFormatters.formatKsh(totalSavings)} with wholesale!",
                                        fontSize = 11.sp,
                                        color = ForestGreenLight,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Text(
                                text = KenyanFormatters.formatKsh(totalPrice),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = ForestGreenPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Product Description
                    Text(
                        text = "Description & Wholesale Notes",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = product.description.ifEmpty { "High-grade standard packaged goods supplied directly by certified Kenyan distributors. Sourced fresh for commercial resale and household use." },
                        fontSize = 13.sp,
                        color = Slate700,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Shipping & Guarantee Info
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalShipping,
                            contentDescription = null,
                            tint = ForestGreenPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Countrywide delivery across all 47 counties in Kenya.",
                            fontSize = 12.sp,
                            color = Slate600
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = GoldAccent,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "100% Genuine Kenyan market products guarantee.",
                            fontSize = 12.sp,
                            color = Slate600
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Bottom Action Buttons
                Surface(
                    shadowElevation = 8.dp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Prominent WhatsApp Order button
                        Button(
                            onClick = {
                                val message = KenyanFormatters.buildProductWhatsAppMessage(
                                    productName = product.name,
                                    quantity = quantity,
                                    unitPrice = unitPrice,
                                    isWholesale = isWholesale,
                                    unit = product.unit
                                )
                                KenyanFormatters.openWhatsAppChat(context, message = message)
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WhatsAppGreen,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("whatsapp_order_dialog_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = "Order via WhatsApp",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Order via WhatsApp (${KenyanFormatters.formatKsh(totalPrice)})",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Add to Cart button
                        Button(
                            onClick = {
                                onAddToCart(product, quantity)
                                onDismiss()
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ForestGreenPrimary,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("add_to_cart_dialog_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Add to Cart",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add $quantity to Cart",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
