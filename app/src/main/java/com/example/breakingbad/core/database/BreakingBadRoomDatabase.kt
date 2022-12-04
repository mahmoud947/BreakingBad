package com.example.breakingbad.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingbad.characters_feature.data.data_source.local.CharactersDao
import com.example.breakingbad.characters_feature.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.characters_feature.data.data_source.local.entities.FavoriteCharacterEntity
import com.example.breakingbad.core.database.converter.Converter
import com.example.breakingbad.quotes_feature.data.data_source.local.QuotesDao
import com.example.breakingbad.quotes_feature.data.data_source.local.entities.QuoteEntity

@Database(
    entities = [CharacterEntity::class,FavoriteCharacterEntity::class ,QuoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class BreakingBadRoomDatabase : RoomDatabase() {
    abstract val charactersDao: CharactersDao
    abstract val quotesDao: QuotesDao
}