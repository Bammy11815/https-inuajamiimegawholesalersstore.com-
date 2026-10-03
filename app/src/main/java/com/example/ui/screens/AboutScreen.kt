package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.StoreFooter
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenOnContainer
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentLight
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.WhatsAppGreen
import com.example.util.KenyanFormatters

data class FaqItem(
    val id: Int,
    val question: String,
    val answer: String
)

@Composable
fun AboutScreen(
    onNavigateToShop: () -> Unit,
    onNavigateToWholesale: () -> Unit,
    onNavigateToContact: () -> Unit,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val expandedFaqs = remember { mutableStateMapOf<Int, Boolean>() }

    val faqs = listOf(
        FaqItem(
            id = 1,
            question = "How does Wholesale vs Retail pricing work?",
            answer = "All items have two transparent tiers: Retail price applies to 1 to 11 units. Wholesale discount automatically activates when you order 12 or more units of an item. For mega orders over 50 units, you can request an institutional bulk quote."
        ),
        FaqItem(
            id = 2,
            question = "How does delivery work across Kenya's 47 counties?",
            answer = "We offer same-day and next-day delivery within Nairobi and surrounding counties (Kiambu, Machakos, Kajiado). For upcountry deliveries across Mombasa, Kisumu, Nakuru, Eldoret, Nyeri, Meru, etc., we dispatch via trusted courier partners (Fargo Courier, Wells Fargo, G4S, and regional bus parcel logistics)."
        ),
        FaqItem(
            id = 3,
            question = "What payment methods do you accept?",
            answer = "We prioritize Kenyan convenience: Pay on delivery via M-Pesa or Cash on delivery! We also support direct M-Pesa Till / Paybill and bank transfer for verified commercial accounts."
        ),
        FaqItem(
            id = 4,
            question = "Can I order directly on WhatsApp or over the phone?",
            answer = "Yes, absolutely! You can tap any 'Order via WhatsApp' button in the app or reach out directly to +254783831157. Our sales desk is active Monday to Saturday from 7:00 AM to 7:00 PM."
        ),
        FaqItem(
            id = 5,
            question = "Are all the brands and products authentic?",
            answer = "100% genuine. We source directly from accredited Kenyan manufacturers and primary FMCG distributors including Unga Limited, Pwani Oil, Bidco, Kensalt, Unilever, and Brookside."
        ),
        FaqItem(
            id = 6,
            question = "Can I return damaged or incorrect goods?",
            answer = "Yes! You can inspect your goods upon delivery. If any item is damaged or incorrect, our delivery agent will immediately replace it or adjust your invoice prior to payment."
        )
    )

    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("about_screen")
    ) {
        // Hero Header
        item {
            Surface(
                color = ForestGreenPrimary,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "About Us",
                        color = GoldAccentLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Inua Jamii Megawholers Store",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Uplifting Kenyan Communities & Enterprises with Quality Commodities at Fair Prices.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Mission & Values Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Our Story & Purpose",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "\"Inua Jamii\" translates to \"Uplifting the Community\". We founded Inua Jamii Megawholesalers Store with a clear mandate: bridge the gap between primary Kenyan food manufacturers and everyday retailers, duka owners, caterers, schools, and families.\n\nBy keeping our margins low and our distribution efficient, we pass substantial savings directly to our customers. Whether you need a single 2kg packet of maize meal for dinner or 50 bales for your retail outlet, we treat every customer with unmatched speed and respect.",
                        fontSize = 13.sp,
                        color = Slate700,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = ForestGreenContainer,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Icon(imageVector = Icons.Default.PriceCheck, contentDescription = null, tint = ForestGreenPrimary)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Fair Pricing", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ForestGreenDark)
                                Text("Direct wholesale tiers without broker markups.", fontSize = 11.sp, color = ForestGreenDark)
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = GoldAccentLight,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Icon(imageVector = Icons.Default.LocalShipping, contentDescription = null, tint = GoldAccent)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Fast Delivery", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                Text("Doorstep and parcel dispatch throughout Kenya.", fontSize = 11.sp, color = Slate700)
                            }
                        }
                    }
                }
            }
        }

        // Customer Reviews Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Customer Testimonials & Reviews",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = "Verified reviews from business owners and families",
                    fontSize = 12.sp,
                    color = Slate500,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                val testimonials = listOf(
                    Triple("David Mutua (Supermarket Manager, Machakos)", "We have partnered with Inua Jamii for 6 months for cooking oil and baking flour supplies. Always accurate orders and great bulk deals.", 5),
                    Triple("Beatrice Ndunge (Bakery Owner, Nairobi)", "Ndovu flour and Mumias sugar delivered right to our bakery on time every Tuesday. Highly recommended for commercial bakers!", 5),
                    Triple("Patrick Kiprono (Retail Duka, Nakuru)", "The WhatsApp ordering is seamless. I send my list in the morning, receive invoice, and parcel arrives safely.", 5)
                )

                testimonials.forEach { (name, quote, rating) ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(name, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ForestGreenPrimary)
                                Row {
                                    repeat(rating) {
                                        Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(13.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("\"$quote\"", fontSize = 12.sp, color = Slate700, lineHeight = 16.sp)
                        }
                    }
                }
            }
        }

        // FAQ Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Frequently Asked Questions (FAQ)",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = "Quick answers to help you order with confidence",
                    fontSize = 12.sp,
                    color = Slate500,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                faqs.forEach { faq ->
                    val isExpanded = expandedFaqs[faq.id] ?: false

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { expandedFaqs[faq.id] = !isExpanded }
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = faq.question,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Slate900,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = ForestGreenPrimary
                                )
                            }

                            AnimatedVisibility(visible = isExpanded) {
                                Column {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    HorizontalDivider(color = Slate200)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = faq.answer,
                                        fontSize = 12.sp,
                                        color = Slate700,
                                        lineHeight = 17.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Direct Help Banner
        item {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = ForestGreenContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Still have questions?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = ForestGreenDark
                    )
                    Text(
                        text = "Our friendly Kenyan customer support is always ready on WhatsApp & Phone.",
                        fontSize = 12.sp,
                        color = ForestGreenOnContainer,
                        modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { KenyanFormatters.openWhatsAppChat(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Chat on WhatsApp", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = { KenyanFormatters.openDialer(context) },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call Us", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Footer
        item {
            StoreFooter(
                onNavigateToShop = onNavigateToShop,
                onNavigateToWholesale = onNavigateToWholesale,
                onNavigateToAbout = { /* Already here */ },
                onNavigateToContact = onNavigateToContact,
                onCategoryClick = onCategoryClick
            )
        }
    }
}
