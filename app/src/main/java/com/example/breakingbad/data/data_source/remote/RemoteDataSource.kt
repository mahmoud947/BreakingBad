package com.example.breakingbad.data.data_source.remote

import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDataSource {

    @GET("characters")
    suspend fun getCharacters(): ArrayList<CharacterDto>

    @GET("characters/{char_id}")
    suspend fun getCharacterById(@Path("char_id") charID: Int): ArrayList<CharacterDto>

    @GET("quotes")
    suspend fun getQuotes(): ArrayList<QuoteDto>
}