package com.example.breakingbad.characters_feature.data.data_source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCharacterEntity(
    @PrimaryKey
    val char_id: Int,
    val appearance: List<Int>,
    val better_call_saul_appearance: List<Int>,
    val birthday: String,
    val category: String,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String
)