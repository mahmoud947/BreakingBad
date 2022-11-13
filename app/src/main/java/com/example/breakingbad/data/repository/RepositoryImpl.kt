package com.example.breakingbad.data.repository

import com.example.breakingbad.data.data_source.local.CharactersDao
import com.example.breakingbad.data.data_source.remote.RemoteDataSource
import com.example.breakingbad.data.mapper.toDomain
import com.example.breakingbad.data.mapper.toEntity
import com.example.breakingbad.domain.repository.Repository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: RemoteDataSource,
    private val dao: CharactersDao
) : Repository {

    override suspend fun getCharacters() =
        dao.getCharacters().map {
            it.toDomain()
        }

    override suspend fun getCharacterById(charID: Int) =
        api.getCharacterById(charID)

    override suspend fun getQuotes() =
        dao.getQuotes().map {
            it.toDomain()
        }

    override suspend fun refreshCharacters() {
        val response = api.getCharacters()
        dao.insertCharacters(* response.toEntity().toTypedArray())
    }

    override suspend fun refreshQuotes() {
        val response = api.getQuotes()
        dao.insertQuotes(* response.toEntity().toTypedArray())
    }
}