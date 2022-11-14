package com.example.breakingbad.data.data_source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
    @PrimaryKey
    val quote_id: Int,
    val author: String,
    val quote: String
)