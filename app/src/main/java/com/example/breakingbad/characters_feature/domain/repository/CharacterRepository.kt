package com.example.breakingbad.characters_feature.domain.repository

import com.example.breakingbad.characters_feature.data.data_source.local.entities.FavoriteCharacterEntity
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<CharacterModel>>

    suspend fun getFavoriteCharacters(): Flow<List<CharacterModel>>

    suspend fun addCharacterToFavorite(characterModel: CharacterModel)

    suspend fun refreshCharacters()


    suspend fun getCharacterById(charID: Int): Flow<CharacterModel>
    suspend fun getFavoriteCharacterById(charID: Int): Flow<CharacterModel>

}
