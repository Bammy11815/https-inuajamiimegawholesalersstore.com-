package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val category: String,
    val retailPrice: Double,       // 1 - 11 units
    val wholesalePrice: Double,    // 12+ units
    val wholesaleMinQuantity: Int = 12,
    val unit: String = "Piece",     // e.g. "Pack", "Bale", "Jerrycan", "Carton", "Bag"
    val stockQuantity: Int = 100,
    val inStock: Boolean = true,
    val description: String = "",
    val imageUrl: String = "",
    val isFeatured: Boolean = false,
    val badge: String? = null       // e.g. "Wholesale Favorite", "Best Seller", "Hot Deal"
) {
    fun getUnitPriceForQuantity(quantity: Int): Double {
        return if (quantity >= wholesaleMinQuantity) wholesalePrice else retailPrice
    }

    fun isWholesaleEligible(quantity: Int): Boolean {
        return quantity >= wholesaleMinQuantity
    }

    fun getSavingsPerUnit(): Double {
        return (retailPrice - wholesalePrice).coerceAtLeast(0.0)
    }

    fun getSavingsPercentage(): Int {
        if (retailPrice <= 0) return 0
        val diff = retailPrice - wholesalePrice
        return ((diff / retailPrice) * 100).toInt().coerceAtLeast(0)
    }
}
