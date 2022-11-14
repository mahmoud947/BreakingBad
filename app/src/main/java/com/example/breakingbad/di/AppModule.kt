package com.example.breakingbad.di

import com.example.breakingbad.data.data_source.local.BreakingBadRoomDatabase
import com.example.breakingbad.data.data_source.remote.RemoteDataSource
import com.example.breakingbad.data.repository.RepositoryImpl
import com.example.breakingbad.domain.repository.Repository
import com.example.breakingbad.domain.use_case.GetCharactersUseCase
import com.example.breakingbad.domain.use_case.GetQuotesUseCase
import com.example.breakingbad.domain.use_case.RefreshCharactersUseCase
import com.example.breakingbad.domain.use_case.RefreshQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRepository(
        api: RemoteDataSource,
        breakingBadRoomDatabase: BreakingBadRoomDatabase
    ): Repository =
        RepositoryImpl(api = api, dao = breakingBadRoomDatabase.charactersDao)


    @Provides
    @Singleton
    fun provideGetCharacterUseCase(repository: Repository) =
        GetCharactersUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(repository: Repository) = GetQuotesUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideRefreshCharactersUseCase(repository: Repository) =
        RefreshCharactersUseCase(repository = repository)


    @Provides
    @Singleton
    fun provideRefreshQuotesUseCase(repository: Repository) =
        RefreshQuotesUseCase(repository = repository)

}