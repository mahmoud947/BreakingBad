package com.example.breakingbad.quotes_feature.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbad.quotes_feature.data.data_source.local.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(vararg quoteEntity: QuoteEntity)

    @Query("SELECT * FROM QuoteEntity")
    fun getQuotes(): Flow<List<QuoteEntity>>

    @Query("DELETE FROM QuoteEntity")
    suspend fun clear()
}