package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.QuoteRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteRequestDao {
    @Query("SELECT * FROM quote_requests ORDER BY createdAt DESC")
    fun getAllQuoteRequests(): Flow<List<QuoteRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuoteRequest(quoteRequest: QuoteRequest): Long

    @Query("UPDATE quote_requests SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Long, status: String)

    @Query("SELECT COUNT(*) FROM quote_requests")
    suspend fun getQuoteCount(): Int
}
