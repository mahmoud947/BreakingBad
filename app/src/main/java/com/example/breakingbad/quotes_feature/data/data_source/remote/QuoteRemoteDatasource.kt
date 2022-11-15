package com.example.breakingbad.quotes_feature.data.data_source.remote

import com.example.breakingbad.quotes_feature.data.data_source.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuoteRemoteDatasource {
    @GET("quotes")
    suspend fun getQuotes(): ArrayList<QuoteDto>
}