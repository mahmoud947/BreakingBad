package com.example.breakingbad.characters_feature.domain.use_case

import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import com.example.breakingbad.characters_feature.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetFavoriteCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): Flow<Resource<Flow<List<CharacterModel>>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getFavoriteCharacters()
            emit(Resource.Success(data = response))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }
}