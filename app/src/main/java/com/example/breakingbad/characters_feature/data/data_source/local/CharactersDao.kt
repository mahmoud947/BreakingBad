package com.example.breakingbad.characters_feature.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbad.characters_feature.data.data_source.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(vararg characterEntity: CharacterEntity)

    @Query("SELECT * FROM CharacterEntity")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clear()
}