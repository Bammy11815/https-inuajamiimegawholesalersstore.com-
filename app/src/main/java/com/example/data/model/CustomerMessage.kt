package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_messages")
data class CustomerMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val phone: String,
    val email: String,
    val subject: String,
    val message: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isRead: Boolean = false
)
