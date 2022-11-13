package com.example.breakingbad.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.breakingbad.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.data.data_source.local.entities.QuoteEntity

@Database(
    entities = [CharacterEntity::class, QuoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BreakingBadRoomDatabase : RoomDatabase(){
    abstract val charactersDao: CharactersDao
}