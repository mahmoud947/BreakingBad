package com.example.breakingbad.characters_feature.domain.use_case

import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import com.example.breakingbad.characters_feature.domain.repository.CharacterRepository
import com.example.breakingbad.core.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AddCharacterToFavoriteUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(characterModel: CharacterModel): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.addCharacterToFavorite(characterModel = characterModel)
            emit(Resource.Success(data = response))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }
}