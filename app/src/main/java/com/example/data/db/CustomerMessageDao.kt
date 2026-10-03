package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CustomerMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerMessageDao {
    @Query("SELECT * FROM customer_messages ORDER BY createdAt DESC")
    fun getAllMessages(): Flow<List<CustomerMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: CustomerMessage): Long

    @Query("UPDATE customer_messages SET isRead = 1 WHERE id = :id")
    suspend fun markAsRead(id: Long)

    @Query("SELECT COUNT(*) FROM customer_messages")
    suspend fun getMessageCount(): Int
}
