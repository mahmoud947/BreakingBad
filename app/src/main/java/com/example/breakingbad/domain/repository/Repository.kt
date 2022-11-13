package com.example.breakingbad.domain.repository

import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.domain.model.CharacterModel
import com.example.breakingbad.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacters(): Flow<List<CharacterModel>>

    suspend fun getCharacterById(charID: Int): ArrayList<CharacterDto>

    suspend fun getQuotes(): Flow<List<QuoteModel>>

    suspend fun refreshCharacters()
    suspend fun refreshQuotes()
}
