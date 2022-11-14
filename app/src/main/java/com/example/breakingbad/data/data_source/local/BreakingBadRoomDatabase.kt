package com.example.breakingbad.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingbad.data.data_source.local.converter.Converter
import com.example.breakingbad.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.data.data_source.local.entities.QuoteEntity

@Database(
    entities = [CharacterEntity::class, QuoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class BreakingBadRoomDatabase : RoomDatabase(){
    abstract val charactersDao: CharactersDao
}