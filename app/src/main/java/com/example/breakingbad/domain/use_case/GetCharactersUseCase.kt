package com.example.breakingbad.domain.use_case

import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.domain.model.CharacterModel
import com.example.breakingbad.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCharactersUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<Resource<Flow<List<CharacterModel>>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getCharacters()
            emit(Resource.Success(data = response))

        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }
}