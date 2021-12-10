package com.example.breakingbad.api

import com.example.breakingbad.model.Character
import com.example.breakingbad.model.Quote
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadApi {

    @GET("characters")
    suspend fun getAllCharacters(): ArrayList<Character>

    @GET("characters/{char_id}")
    suspend fun getCharacterById(@Path("char_id") charID: Int): ArrayList<Character>

    @GET("quotes")
    suspend fun getAllQuotes(): ArrayList<Quote>
}