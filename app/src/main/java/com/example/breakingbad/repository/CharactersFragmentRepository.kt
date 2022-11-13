package com.example.breakingbad.repository

import com.example.breakingbad.data.data_source.remote.RemoteDataSource
import javax.inject.Inject

class CharactersFragmentRepository @Inject constructor(
    private val breakingBadApi: RemoteDataSource,
) {

    suspend fun getAllCharacters() =
        breakingBadApi.getCharacters()

     suspend fun getCharacterById(charID: Int) =
        breakingBadApi.getCharacterById(charID)

    suspend fun getAllQuote() =
        breakingBadApi.getQuotes()
}