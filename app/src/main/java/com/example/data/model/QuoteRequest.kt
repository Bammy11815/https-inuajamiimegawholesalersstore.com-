package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_requests")
data class QuoteRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val businessName: String,
    val contactPerson: String,
    val phone: String,
    val email: String = "",
    val county: String,
    val estimatedUnits: Int,
    val productsRequested: String,
    val additionalNotes: String = "",
    val status: String = "Pending",
    val createdAt: Long = System.currentTimeMillis()
)
