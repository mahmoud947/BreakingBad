package com.example.breakingbad.quotes_feature.domain.use_case


import com.example.breakingbad.quotes_feature.domain.model.QuoteModel
import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.quotes_feature.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetQuotesUseCase(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(): Flow<Resource<Flow<List<QuoteModel>>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getQuotes()
            emit(Resource.Success(data = response))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }
}