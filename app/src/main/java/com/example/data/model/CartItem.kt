package com.example.data.model

data class CartItem(
    val productId: Long,
    val productName: String,
    val category: String,
    val unit: String,
    val quantity: Int,
    val retailPrice: Double,
    val wholesalePrice: Double,
    val wholesaleMinQuantity: Int = 12,
    val imageUrl: String = ""
) {
    val isWholesaleApplied: Boolean
        get() = quantity >= wholesaleMinQuantity

    val unitPrice: Double
        get() = if (isWholesaleApplied) wholesalePrice else retailPrice

    val lineTotal: Double
        get() = unitPrice * quantity

    val potentialWholesaleSavings: Double
        get() = if (isWholesaleApplied) (retailPrice - wholesalePrice) * quantity else 0.0

    val unitsNeededForWholesale: Int
        get() = (wholesaleMinQuantity - quantity).coerceAtLeast(0)
}
