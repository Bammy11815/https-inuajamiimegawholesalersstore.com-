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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.data.model.Product
import com.example.ui.components.ProductCard
import com.example.ui.components.StoreFooter
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenOnContainer
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentDark
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

@Composable
fun WholesaleScreen(
    wholesaleProducts: List<Product>,
    quoteSubmissionSuccess: String?,
    onSubmitQuote: (businessName: String, contactPerson: String, phone: String, email: String, county: String, units: Int, products: String, notes: String) -> Unit,
    onClearSuccess: () -> Unit,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToContact: () -> Unit,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Form states
    var businessName by remember { mutableStateOf("") }
    var contactPerson by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var county by remember { mutableStateOf("") }
    var estimatedUnits by remember { mutableStateOf("50") }
    var productsRequested by remember { mutableStateOf("") }
    var additionalNotes by remember { mutableStateOf("") }
    var formError by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("wholesale_screen")
    ) {
        // Wholesale Hero Banner
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
                    Surface(
                        color = GoldAccent,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "WHOLESALE PORTAL",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Wholesale Pricing That Empowers Your Business",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Whether you operate a neighborhood duka, supermarket, hotel, catering business, or school in Kenya, Inua Jamii Megawholesalers delivers staple commodities at direct factory prices.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Tier Pricing Explanation Card
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
                        text = "How Our Pricing Works",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Retail Tier Box
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = Color(0xFFF8FAFC),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Slate200),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Retail Tier",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate700
                                )
                                Text(
                                    text = "1 – 11 Units",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Slate900
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Standard consumer shelf price for households",
                                    fontSize = 10.sp,
                                    color = Slate500
                                )
                            }
                        }

                        // Wholesale Tier Box
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = ForestGreenContainer,
                            border = androidx.compose.foundation.BorderStroke(1.dp, ForestGreenLight),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Wholesale Tier",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ForestGreenDark
                                )
                                Text(
                                    text = "12+ Units",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = ForestGreenPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Save 15% – 25% per unit. Auto applied!",
                                    fontSize = 10.sp,
                                    color = ForestGreenDark
                                )
                            }
                        }

                        // Super Bulk Tier Box
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = GoldAccentLight,
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldAccent),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Super Bulk",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldAccentDark
                                )
                                Text(
                                    text = "50+ Units",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = GoldAccentDark
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Request custom negotiated quotation",
                                    fontSize = 10.sp,
                                    color = GoldAccentDark
                                )
                            }
                        }
                    }
                }
            }
        }

        // Wholesale Benefits List
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Wholesale Partner Benefits",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Spacer(modifier = Modifier.height(10.dp))

                val benefits = listOf(
                    "Transparent Bales & Crates Pricing" to "No hidden markup, direct access to bale and carton rates.",
                    "Countrywide Distribution" to "We dispatch to Nairobi, Mombasa, Kisumu, Nakuru, Eldoret & all 47 counties.",
                    "Fast WhatsApp Order Dispatch" to "Dedicated WhatsApp account line at +254783831157 for instant orders.",
                    "M-Pesa Till & Invoicing" to "Official receipts, invoices, and verified M-Pesa payment options."
                )

                benefits.forEach { (title, desc) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = ForestGreenPrimary,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate800
                            )
                            Text(
                                text = desc,
                                fontSize = 11.sp,
                                color = Slate600
                            )
                        }
                    }
                }
            }
        }

        // Top Wholesale Products Spotlight
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular Wholesale Staples",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "View All Shop →",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestGreenPrimary,
                        modifier = Modifier.clickable { onNavigateToShop() }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        items(wholesaleProducts.take(4)) { product ->
            Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                ProductCard(
                    product = product,
                    onProductClick = onProductClick,
                    onAddToCart = onAddToCart
                )
            }
        }

        // Bulk Quote Request Form
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .testTag("quote_request_form_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Request a Custom Wholesale Quote",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "For large quantities (50+ units) or specialized institutional supplies. We respond within 2 business hours.",
                        fontSize = 12.sp,
                        color = Slate600,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    if (quoteSubmissionSuccess != null) {
                        Surface(
                            color = ForestGreenContainer,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Quote Request Submitted!",
                                    color = ForestGreenDark,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = quoteSubmissionSuccess,
                                    color = ForestGreenOnContainer,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(
                                        onClick = {
                                            val quoteMsg = """
                                                Hello Inua Jamii Megawholers Store,
                                                I have submitted a bulk quote request:
                                                • Business: $businessName
                                                • Contact: $contactPerson ($phone)
                                                • Products: $productsRequested
                                                • Estimated Units: $estimatedUnits
                                                Kindly send me your wholesale quotation.
                                            """.trimIndent()
                                            KenyanFormatters.openWhatsAppChat(context, message = quoteMsg)
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Send to WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }

                                    OutlinedButton(
                                        onClick = onClearSuccess,
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Done", fontSize = 11.sp)
                                    }
                                }
                            }
                        }
                    }

                    if (formError != null) {
                        Text(
                            text = formError!!,
                            color = Color.Red,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    // Form Fields
                    OutlinedTextField(
                        value = businessName,
                        onValueChange = { businessName = it },
                        label = { Text("Business / Store / School Name *") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("quote_business_name_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = contactPerson,
                        onValueChange = { contactPerson = it },
                        label = { Text("Contact Person Name *") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("quote_contact_person_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone / WhatsApp Number (e.g. 0712345678) *") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("quote_phone_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address (Optional)") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = ForestGreenPrimary) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = county,
                            onValueChange = { county = it },
                            label = { Text("County / Town *") },
                            leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = ForestGreenPrimary) },
                            singleLine = true,
                            modifier = Modifier.weight(1f).testTag("quote_county_field")
                        )

                        OutlinedTextField(
                            value = estimatedUnits,
                            onValueChange = { estimatedUnits = it },
                            label = { Text("Total Units *") },
                            leadingIcon = { Icon(imageVector = Icons.Default.Numbers, contentDescription = null, tint = ForestGreenPrimary) },
                            singleLine = true,
                            modifier = Modifier.weight(1f).testTag("quote_units_field")
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = productsRequested,
                        onValueChange = { productsRequested = it },
                        label = { Text("List Products Needed (e.g. Pembe 2kg 20 bales, Rina 5L 15 cans) *") },
                        minLines = 2,
                        maxLines = 4,
                        modifier = Modifier.fillMaxWidth().testTag("quote_products_field")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = additionalNotes,
                        onValueChange = { additionalNotes = it },
                        label = { Text("Delivery Schedule or Special Instructions") },
                        minLines = 2,
                        maxLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            if (businessName.isBlank() || contactPerson.isBlank() || phone.isBlank() || county.isBlank() || productsRequested.isBlank()) {
                                formError = "Please fill in all required fields marked with *"
                            } else {
                                formError = null
                                val units = estimatedUnits.toIntOrNull() ?: 50
                                onSubmitQuote(
                                    businessName,
                                    contactPerson,
                                    phone,
                                    email,
                                    county,
                                    units,
                                    productsRequested,
                                    additionalNotes
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("submit_quote_button")
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Submit Wholesale Quote Request", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Footer
        item {
            StoreFooter(
                onNavigateToShop = onNavigateToShop,
                onNavigateToWholesale = { /* Already here */ },
                onNavigateToAbout = onNavigateToAbout,
                onNavigateToContact = onNavigateToContact,
                onCategoryClick = onCategoryClick
            )
        }
    }
}
