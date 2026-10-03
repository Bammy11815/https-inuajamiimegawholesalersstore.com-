package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Sanitizer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.Product
import com.example.ui.components.ProductCard
import com.example.ui.components.StoreFooter
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentDark
import com.example.ui.theme.GoldAccentLight
import com.example.ui.theme.MPesaGreen
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

@Composable
fun HomeScreen(
    featuredProducts: List<Product>,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToWholesale: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToContact: () -> Unit,
    onSelectCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen")
    ) {
        // Hero Section
        item(span = { GridItemSpan(2) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                // Background image or gradient
                Image(
                    painter = painterResource(id = R.drawable.img_hero_banner),
                    contentDescription = "Kenyan Store Shelf Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay for contrast and readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    ForestGreenDark.copy(alpha = 0.85f),
                                    ForestGreenPrimary.copy(alpha = 0.80f),
                                    Slate900.copy(alpha = 0.95f)
                                )
                            )
                        )
                )

                // Hero Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = GoldAccent,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = "KENYA'S TRUSTED WHOLESALER & RETAILER",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = "Inua Jamii Megawholers Store",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Quality Products. Great Prices. Wholesale & Retail.",
                        color = GoldAccentLight,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Shop household essentials, food staples, cooking oils, flour, sugar, beverages, and personal care online with fast doorstep delivery across Kenya.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = onNavigateToShop,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldAccent,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("hero_shop_now_btn")
                        ) {
                            Text(text = "Shop Now", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }

                        Button(
                            onClick = onNavigateToWholesale,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ForestGreenLight,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("hero_wholesale_deals_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Percent,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Wholesale Deals", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }
        }

        // Value Proposition Badges
        item(span = { GridItemSpan(2) }) {
            Surface(
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TrustFeatureItem(
                        icon = Icons.Default.Inventory,
                        title = "Wholesale Tiers",
                        subtitle = "From 12+ Units"
                    )
                    TrustFeatureItem(
                        icon = Icons.Default.LocalShipping,
                        title = "Countrywide",
                        subtitle = "47 Counties"
                    )
                    TrustFeatureItem(
                        icon = Icons.Default.Payments,
                        title = "M-Pesa / Cash",
                        subtitle = "On Delivery"
                    )
                    TrustFeatureItem(
                        icon = Icons.Default.Verified,
                        title = "100% Genuine",
                        subtitle = "Kenyan Brands"
                    )
                }
            }
        }

        // WhatsApp Order Callout Banner
        item(span = { GridItemSpan(2) }) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = ForestGreenPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Need Quick Bulk Ordering?",
                            color = GoldAccentLight,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Order Directly via WhatsApp",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Chat with our sales team at +254783831157 for fast assistance",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    }

                    Button(
                        onClick = {
                            KenyanFormatters.openWhatsAppChat(
                                context,
                                message = "Hello Inua Jamii Megawholers Store, I am inquiring about product prices and placing a wholesale/retail order."
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("home_whatsapp_chat_banner_btn")
                    ) {
                        Text("Chat Now", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Categories Carousel Section
        item(span = { GridItemSpan(2) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Browse Categories",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "View All →",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestGreenPrimary,
                        modifier = Modifier.clickable { onNavigateToCategories() }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                val categories = listOf(
                    "Food & Groceries" to Icons.Default.Fastfood,
                    "Cooking Oil" to Icons.Default.LocalMall,
                    "Flour & Cereals" to Icons.Default.Category,
                    "Sugar & Salt" to Icons.Default.LocalMall,
                    "Tea & Spices" to Icons.Default.LocalDrink,
                    "Cleaning Products" to Icons.Default.CleaningServices,
                    "Personal Care" to Icons.Default.Sanitizer,
                    "Beverages" to Icons.Default.LocalDrink
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    items(categories) { (categoryName, icon) ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White,
                            shadowElevation = 1.5.dp,
                            modifier = Modifier
                                .width(120.dp)
                                .clickable {
                                    onSelectCategory(categoryName)
                                    onNavigateToShop()
                                }
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .background(ForestGreenContainer, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = categoryName,
                                        tint = ForestGreenPrimary,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = categoryName,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Slate800,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section Title: Featured Wholesale Deals
        item(span = { GridItemSpan(2) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Wholesale & Retail Deals",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "Save up to 25% when buying 12+ units",
                        fontSize = 12.sp,
                        color = ForestGreenPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }

                OutlinedButton(
                    onClick = onNavigateToShop,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Text("See All", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Product Cards Grid
        items(featuredProducts, key = { it.id }) { product ->
            Box(modifier = Modifier.padding(horizontal = 6.dp)) {
                ProductCard(
                    product = product,
                    onProductClick = onProductClick,
                    onAddToCart = onAddToCart
                )
            }
        }

        // Wholesale Request a Quote Promo Card
        item(span = { GridItemSpan(2) }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Slate900,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Surface(
                        color = GoldAccent,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "BULK INSTITUTIONAL ORDERS",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Need Supplies for a School, Hotel, Supermarket, or Duka?",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Get customized wholesale quotes, flexible scheduled deliveries, and dedicated account support for bulk orders over 50 units.",
                        color = Slate400,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = onNavigateToWholesale,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ForestGreenPrimary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("home_request_bulk_quote_btn")
                    ) {
                        Text("Request a Bulk Quote", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        // Customer Reviews Section
        item(span = { GridItemSpan(2) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Customer Reviews",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = "Trusted by shopkeepers and families across Kenya",
                    fontSize = 12.sp,
                    color = Slate500
                )

                Spacer(modifier = Modifier.height(12.dp))

                val reviews = listOf(
                    Triple(
                        "Mary Wanjiku (Duka Owner, Thika)",
                        "Inua Jamii Megawholesalers has transformed my shop stocking. Their wholesale bale prices for Pembe and Mumias sugar are the best in the market, and delivery to Thika was prompt!",
                        5
                    ),
                    Triple(
                        "Ahmed Hassan (Restaurant Manager, Eastleigh)",
                        "Ordering cooking oil and rice via WhatsApp is super convenient. We save thousands every month on wholesale 10L cooking oil crates.",
                        5
                    ),
                    Triple(
                        "Grace Achieng (Household Shopper, Nairobi)",
                        "Genuine products, fast delivery, and paying via M-Pesa on arrival gives me total peace of mind. Excellent customer service.",
                        5
                    )
                )

                reviews.forEach { (author, comment, stars) ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = author,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ForestGreenPrimary
                                )
                                Row {
                                    repeat(stars) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = GoldAccent,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "\"$comment\"",
                                fontSize = 12.sp,
                                color = Slate700,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Store Footer
        item(span = { GridItemSpan(2) }) {
            StoreFooter(
                onNavigateToShop = onNavigateToShop,
                onNavigateToWholesale = onNavigateToWholesale,
                onNavigateToAbout = onNavigateToAbout,
                onNavigateToContact = onNavigateToContact,
                onCategoryClick = { cat ->
                    onSelectCategory(cat)
                    onNavigateToShop()
                }
            )
        }
    }
}

@Composable
private fun TrustFeatureItem(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(ForestGreenContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = ForestGreenPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate900
        )
        Text(
            text = subtitle,
            fontSize = 9.sp,
            color = Slate500
        )
    }
}
