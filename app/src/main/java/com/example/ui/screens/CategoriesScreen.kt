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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Sanitizer
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Product
import com.example.ui.components.StoreFooter
import com.example.ui.theme.ForestGreenContainer
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenLight
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentLight
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

data class CategoryMeta(
    val name: String,
    val description: String,
    val icon: ImageVector,
    val popularItems: String
)

@Composable
fun CategoriesScreen(
    products: List<Product>,
    onSelectCategory: (String) -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToWholesale: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryList = listOf(
        CategoryMeta(
            name = "Food & Groceries",
            description = "Staple food items, rice, pulses, canned goods",
            icon = Icons.Default.Fastfood,
            popularItems = "Daawat Basmati, Pasta, Grains"
        ),
        CategoryMeta(
            name = "Cooking Oil",
            description = "Refined vegetable oils, palm olein in 3L, 5L, 10L, 20L",
            icon = Icons.Default.LocalMall,
            popularItems = "Rina 5L, Salit 10L, Golden Fry"
        ),
        CategoryMeta(
            name = "Flour & Cereals",
            description = "Fortified maize flour & all-purpose wheat flour bales",
            icon = Icons.Default.Grain,
            popularItems = "Pembe 2kg, Jogoo, Ndovu Baking"
        ),
        CategoryMeta(
            name = "Sugar & Salt",
            description = "Pure cane sugar bales and iodized table salt",
            icon = Icons.Default.LocalMall,
            popularItems = "Mumias White Sugar, Kensalt"
        ),
        CategoryMeta(
            name = "Tea & Spices",
            description = "Kenyan highland tea, pilau spices & stew seasonings",
            icon = Icons.Default.LocalDrink,
            popularItems = "Ketepa Pride, Royco Mchuzi Mix"
        ),
        CategoryMeta(
            name = "Beverages",
            description = "Long life UHT milk crates and soft drink cases",
            icon = Icons.Default.LocalDrink,
            popularItems = "Brookside Milk Crates, Coca-Cola"
        ),
        CategoryMeta(
            name = "Cleaning Products",
            description = "Washing powder detergents, dish liquids & bar soaps",
            icon = Icons.Default.CleaningServices,
            popularItems = "Omo 1kg, Sunlight Lemon, Menengai"
        ),
        CategoryMeta(
            name = "Personal Care",
            description = "Family bathing soaps, toothpaste & hygiene items",
            icon = Icons.Default.Sanitizer,
            popularItems = "Geisha Soap, Colgate 140g"
        ),
        CategoryMeta(
            name = "Household Products",
            description = "Toilet paper packs, paper towels & household supplies",
            icon = Icons.Default.Home,
            popularItems = "Velvex 10 Rolls, Serviettes"
        ),
        CategoryMeta(
            name = "Other Essentials",
            description = "Shaving razors, batteries, matches & wholesale accessories",
            icon = Icons.Default.Inventory,
            popularItems = "Bic Shavers Card, Matches, Foil"
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .testTag("categories_screen")
    ) {
        // Header Banner
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = "Product Categories",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = "Explore all 10 departments with retail and wholesale pricing",
                    fontSize = 13.sp,
                    color = Slate500,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        // Category Cards
        items(categoryList) { category ->
            val count = products.count { it.category.equals(category.name, ignoreCase = true) }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelectCategory(category.name)
                        onNavigateToShop()
                    }
                    .testTag("category_card_${category.name}")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(ForestGreenContainer, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.name,
                            tint = ForestGreenPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = category.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFFF1F5F9)
                            ) {
                                Text(
                                    text = if (count > 0) "$count items" else "In stock",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ForestGreenPrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = category.description,
                            fontSize = 12.sp,
                            color = Slate700
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Popular: ${category.popularItems}",
                            fontSize = 11.sp,
                            color = GoldAccent,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "View products",
                        tint = Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // Footer
        item {
            Spacer(modifier = Modifier.height(14.dp))
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
