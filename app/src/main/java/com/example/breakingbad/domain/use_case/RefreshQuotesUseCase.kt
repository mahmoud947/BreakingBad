package com.example.breakingbad.domain.use_case

import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RefreshQuotesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.refreshQuotes()
            emit(Resource.Success(data = response))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }
}