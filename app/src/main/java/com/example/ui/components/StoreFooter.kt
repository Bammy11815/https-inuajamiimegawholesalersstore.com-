package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.WhatsAppGreen
import com.example.util.KenyanFormatters

@Composable
fun StoreFooter(
    onNavigateToShop: () -> Unit,
    onNavigateToWholesale: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToContact: () -> Unit,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        color = Slate900,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Store Title & Tagline
            Text(
                text = "Inua Jamii Megawholers Store",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Quality Products. Great Prices. Wholesale & Retail Across Kenya.",
                color = GoldAccent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Quick contact action buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Phone
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { KenyanFormatters.openDialer(context) }
                        .padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone",
                        tint = GoldAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Phone: +254783831157",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }

                // WhatsApp
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { KenyanFormatters.openWhatsAppChat(context) }
                        .padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "WhatsApp",
                        tint = WhatsAppGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "WhatsApp: +254783831157",
                        color = WhatsAppGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Email
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { KenyanFormatters.openEmail(context) }
                        .padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = GoldAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Email: megawholesalers12@gmail.com",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }

                // Business Hours
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Business Hours",
                        tint = Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mon - Sat: 7:00 AM - 7:00 PM | Sun: 9:00 AM - 4:00 PM",
                        color = Slate400,
                        fontSize = 12.sp
                    )
                }
            }

            HorizontalDivider(
                color = Slate800,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Popular Categories
            Text(
                text = "Product Categories",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val categories = listOf(
                "Food & Groceries", "Cooking Oil", "Flour & Cereals",
                "Sugar & Salt", "Tea & Spices", "Cleaning Products", "Beverages"
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    categories.take(4).forEach { cat ->
                        Text(
                            text = "• $cat",
                            color = Slate400,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clickable { onCategoryClick(cat) }
                                .padding(vertical = 3.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    categories.drop(4).forEach { cat ->
                        Text(
                            text = "• $cat",
                            color = Slate400,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clickable { onCategoryClick(cat) }
                                .padding(vertical = 3.dp)
                        )
                    }
                }
            }

            HorizontalDivider(
                color = Slate800,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Quick navigation links
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Shop All",
                    color = GoldAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onNavigateToShop() }
                )
                Text(
                    text = "Wholesale Hub",
                    color = GoldAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onNavigateToWholesale() }
                )
                Text(
                    text = "About Us",
                    color = GoldAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onNavigateToAbout() }
                )
                Text(
                    text = "Contact",
                    color = GoldAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onNavigateToContact() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Payment and Trust Badges
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Slate800, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Secure Payment",
                    tint = ForestGreenLight,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Accepted: M-Pesa on Delivery • Till / Paybill • Cash on Delivery",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Copyright notice
            Text(
                text = "© 2026 Inua Jamii Megawholers Store. All Rights Reserved. Kenya.",
                color = Slate600,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
