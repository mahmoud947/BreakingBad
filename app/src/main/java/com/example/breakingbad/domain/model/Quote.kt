package com.example.breakingbad.domain.model

import androidx.room.Entity

@Entity
data class Quote(
    val author: String,
    val quote: String,
    val quote_id: Int
)