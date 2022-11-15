package com.example.breakingbad.di

import android.app.Application
import androidx.room.Room
import com.example.breakingbad.core.database.BreakingBadRoomDatabase
import com.example.breakingbad.core.util.Constant.Companion.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            BreakingBadRoomDatabase::class.java,
            DATA_BASE_NAME
        ).build()
}