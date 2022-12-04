package com.example.breakingbad.characters_feature.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.breakingbad.characters_feature.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.characters_feature.data.data_source.local.entities.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(vararg characterEntity: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacterToFavorite(favoriteCharacterEntity: FavoriteCharacterEntity)

    @Query("SELECT * FROM FavoriteCharacterEntity")
    fun getFavoriteCharacters(): Flow<List<FavoriteCharacterEntity>>

    @Query("SELECT * FROM CharacterEntity")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity WHERE char_id = :id")
    fun getCharacterInfo(id: Int): Flow<CharacterEntity>

    @Query("SELECT * FROM FavoriteCharacterEntity WHERE char_id = :id")
    fun getFavoriteCharacterInfo(id: Int): Flow<FavoriteCharacterEntity>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clear()
}