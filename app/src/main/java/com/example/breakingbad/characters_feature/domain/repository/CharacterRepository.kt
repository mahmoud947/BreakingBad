package com.example.breakingbad.characters_feature.domain.repository

import com.example.breakingbad.characters_feature.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<CharacterModel>>

    suspend fun getCharacterById(charID: Int): ArrayList<CharacterDto>

    suspend fun refreshCharacters()

}
