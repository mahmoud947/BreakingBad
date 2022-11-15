package com.example.breakingbad.di

import com.example.breakingbad.core.database.BreakingBadRoomDatabase
import com.example.breakingbad.quotes_feature.data.data_source.remote.QuoteRemoteDatasource
import com.example.breakingbad.quotes_feature.data.repository.QuotesRepositoryImpl
import com.example.breakingbad.quotes_feature.domain.repository.QuotesRepository
import com.example.breakingbad.quotes_feature.domain.use_case.GetQuotesUseCase
import com.example.breakingbad.quotes_feature.domain.use_case.RefreshQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

    @Provides
    @Singleton
    fun provideQuoteApi(
        retrofit: Retrofit
    ): QuoteRemoteDatasource =
        retrofit.create(QuoteRemoteDatasource::class.java)


    @Provides
    @Singleton
    fun provideQuotesRepository(
        api: QuoteRemoteDatasource,
        breakingBadRoomDatabase: BreakingBadRoomDatabase
    ): QuotesRepository = QuotesRepositoryImpl(api = api, dao = breakingBadRoomDatabase.quotesDao)

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(repository: QuotesRepository) =
        GetQuotesUseCase(repository = repository)


    @Provides
    @Singleton
    fun provideRefreshQuotesUseCase(repository: QuotesRepository) =
        RefreshQuotesUseCase(repository = repository)

}