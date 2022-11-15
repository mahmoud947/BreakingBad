package com.example.breakingbad.characters_feature.data.repository

import com.example.breakingbad.characters_feature.data.data_source.local.CharactersDao
import com.example.breakingbad.characters_feature.data.data_source.remote.CharacterRemoteDatasource
import com.example.breakingbad.characters_feature.data.mapper.toDomain
import com.example.breakingbad.characters_feature.data.mapper.toEntity
import com.example.breakingbad.characters_feature.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val api: CharacterRemoteDatasource,
    private val dao: CharactersDao
) : CharacterRepository {

    override suspend fun getCharacters() =
        dao.getCharacters().map {
            it.toDomain()
        }

    override suspend fun getCharacterById(charID: Int) =
        api.getCharacterById(charID)


    override suspend fun refreshCharacters() {
        val response = api.getCharacters()
        dao.insertCharacters(* response.toEntity().toTypedArray())
    }


}