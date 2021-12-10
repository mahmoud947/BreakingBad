package com.example.breakingbad.repository

import com.example.breakingbad.api.BreakingBadApi
import javax.inject.Inject

class CharactersFragmentRepository @Inject constructor(
    private val breakingBadApi: BreakingBadApi,
) {

    suspend fun getAllCharacters() =
        breakingBadApi.getAllCharacters()

    suspend fun getCharacterById(charID: Int) =
        breakingBadApi.getCharacterById(charID)

    suspend fun getAllQuote() =
        breakingBadApi.getAllQuotes()
}