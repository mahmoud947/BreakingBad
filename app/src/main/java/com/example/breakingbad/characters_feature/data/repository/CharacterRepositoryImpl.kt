package com.example.breakingbad.characters_feature.data.repository

import com.example.breakingbad.characters_feature.data.data_source.local.CharactersDao
import com.example.breakingbad.characters_feature.data.data_source.remote.CharacterRemoteDatasource
import com.example.breakingbad.characters_feature.data.mapper.toCharacterEntity
import com.example.breakingbad.characters_feature.data.mapper.toDomain
import com.example.breakingbad.characters_feature.data.mapper.toEntity
import com.example.breakingbad.characters_feature.data.mapper.toFavoriteEntity
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import com.example.breakingbad.characters_feature.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val api: CharacterRemoteDatasource,
    private val dao: CharactersDao
) : CharacterRepository {

    override suspend fun getCharacters() =
        dao.getCharacters().map {
            it.toDomain()
        }

    override suspend fun getFavoriteCharacters(): Flow<List<CharacterModel>> =
        dao.getFavoriteCharacters().map {
            it.toDomain()
        }

    override suspend fun addCharacterToFavorite(characterModel: CharacterModel) {
        dao.addCharacterToFavorite(characterModel.toEntity().toFavoriteEntity())
    }

    override suspend fun getCharacterById(charID: Int) =
        dao.getCharacterInfo(charID).map {
            it.toDomain()
        }

    override suspend fun getFavoriteCharacterById(charID: Int): Flow<CharacterModel> =
        dao.getFavoriteCharacterInfo(charID).map {
            it.toCharacterEntity().toDomain()
        }



    override suspend fun refreshCharacters() {
        val response = api.getCharacters()
        dao.insertCharacters(* response.toEntity().toTypedArray())
    }


}