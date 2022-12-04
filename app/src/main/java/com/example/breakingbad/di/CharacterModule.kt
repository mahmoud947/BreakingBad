package com.example.breakingbad.di

import com.example.breakingbad.characters_feature.data.data_source.remote.CharacterRemoteDatasource
import com.example.breakingbad.characters_feature.data.repository.CharacterRepositoryImpl
import com.example.breakingbad.characters_feature.domain.repository.CharacterRepository
import com.example.breakingbad.characters_feature.domain.use_case.*
import com.example.breakingbad.core.database.BreakingBadRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): CharacterRemoteDatasource =
        retrofit.create(CharacterRemoteDatasource::class.java)


    @Provides
    @Singleton
    fun provideCharactersRepository(
        api: CharacterRemoteDatasource,
        breakingBadRoomDatabase: BreakingBadRoomDatabase
    ): CharacterRepository =
        CharacterRepositoryImpl(api = api, dao = breakingBadRoomDatabase.charactersDao)


    @Provides
    @Singleton
    fun provideGetCharacterUseCase(repository: CharacterRepository) =
        GetCharactersUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideGetFavoriteCharacterUseCase(repository: CharacterRepository) =
        GetFavoriteCharactersUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideRefreshCharactersUseCase(repository: CharacterRepository) =
        RefreshCharactersUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideGetCharacterInfoUseCase(repository: CharacterRepository) =
        GetCharacterInfoUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideAddCharacterToFavoriteUseCase(repository: CharacterRepository) =
        AddCharacterToFavoriteUseCase(repository = repository)


}