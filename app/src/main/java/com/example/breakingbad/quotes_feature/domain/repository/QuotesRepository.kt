package com.example.breakingbad.quotes_feature.domain.repository

import com.example.breakingbad.quotes_feature.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    suspend fun getQuotes(): Flow<List<QuoteModel>>

    suspend fun refreshQuotes()
}