package com.example.breakingbad.data.data_source.local.entities

import androidx.room.Entity

@Entity
data class QuoteEntity(
    val author: String,
    val quote: String,
    val quote_id: Int
)