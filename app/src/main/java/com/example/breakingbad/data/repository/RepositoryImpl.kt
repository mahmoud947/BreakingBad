package com.example.breakingbad.data.repository

import com.example.breakingbad.data.data_source.remote.RemoteDataSource
import com.example.breakingbad.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val breakingBadApi: RemoteDataSource,
) : Repository {

    override suspend fun getAllCharacters() =
        breakingBadApi.getCharacters()

     override suspend fun getCharacterById(charID: Int) =
        breakingBadApi.getCharacterById(charID)

    override suspend fun getAllQuote() =
        breakingBadApi.getQuotes()
}