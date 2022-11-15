package com.example.breakingbad.quotes_feature.data.repository

import com.example.breakingbad.quotes_feature.data.data_source.local.QuotesDao
import com.example.breakingbad.quotes_feature.data.data_source.remote.QuoteRemoteDatasource
import com.example.breakingbad.quotes_feature.data.mapper.toDomain
import com.example.breakingbad.quotes_feature.data.mapper.toEntity
import com.example.breakingbad.quotes_feature.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.map

class QuotesRepositoryImpl(
    private val api: QuoteRemoteDatasource,
    private val dao: QuotesDao
) : QuotesRepository {

    override suspend fun getQuotes() =
        dao.getQuotes().map {
            it.toDomain()
        }

    override suspend fun refreshQuotes() {
        val response = api.getQuotes()
        dao.insertQuotes(* response.toEntity().toTypedArray())
    }
}