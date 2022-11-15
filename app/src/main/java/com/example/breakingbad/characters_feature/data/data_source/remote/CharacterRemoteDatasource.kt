package com.example.breakingbad.characters_feature.data.data_source.remote

import com.example.breakingbad.characters_feature.data.data_source.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRemoteDatasource {

    @GET("characters")
    suspend fun getCharacters(): ArrayList<CharacterDto>

    @GET("characters/{char_id}")
    suspend fun getCharacterById(@Path("char_id") charID: Int): ArrayList<CharacterDto>

}