package com.example.breakingbad.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbad.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.data.data_source.local.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(vararg characterEntity: CharacterEntity)

    @Query("SELECT * FROM CharacterEntity")
     fun getCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(vararg quoteEntity: QuoteEntity)

    @Query("SELECT * FROM QuoteEntity")
     fun getQuotes(): Flow<List<QuoteEntity>>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clear()
}