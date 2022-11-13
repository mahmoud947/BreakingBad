package com.example.breakingbad.domain.model


data class CharacterModel(
    val appearance: List<Any>,
    val better_call_saul_appearance: List<Int>,
    val birthday: String,
    val category: String,
    val char_id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String
)