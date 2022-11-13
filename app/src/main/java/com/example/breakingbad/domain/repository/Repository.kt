package com.example.breakingbad.domain.repository

import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto

interface Repository {
    suspend fun getAllCharacters(): ArrayList<CharacterDto>

    suspend fun getCharacterById(charID: Int): ArrayList<CharacterDto>

    suspend fun getAllQuote(): ArrayList<QuoteDto>
}
