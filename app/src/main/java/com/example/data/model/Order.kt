package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderNumber: String,            // e.g. "IJM-8041"
    val customerName: String,
    val customerPhone: String,
    val customerEmail: String = "",
    val county: String,
    val town: String,
    val deliveryAddress: String,
    val deliveryInstructions: String = "",
    val itemsSummary: String,           // Full formatted order breakdown
    val subtotal: Double,
    val deliveryFee: Double,
    val totalAmount: Double,
    val paymentMethod: String,          // "M-Pesa on Delivery", "M-Pesa Till / Paybill", "Cash on Delivery"
    val orderStatus: String = "Pending", // "Pending", "Confirmed", "Processing", "Dispatched", "Delivered", "Cancelled"
    val orderType: String = "Retail",   // "Retail", "Wholesale", "Mixed"
    val createdAt: Long = System.currentTimeMillis()
)
