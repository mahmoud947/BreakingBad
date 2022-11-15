package com.example.breakingbad.quotes_feature.data.mapper

import com.example.breakingbad.quotes_feature.data.data_source.local.entities.QuoteEntity
import com.example.breakingbad.quotes_feature.data.data_source.remote.dto.QuoteDto
import com.example.breakingbad.quotes_feature.domain.model.QuoteModel

fun QuoteDto.toEntity(): QuoteEntity = QuoteEntity(
    author = this.author,
    quote = this.quote,
    quote_id = this.quote_id
)

fun QuoteEntity.toDomain(): QuoteModel = QuoteModel(
    author = this.author,
    quote = this.quote,
    quote_id = this.quote_id
)

fun QuoteModel.toEntity(): QuoteEntity = QuoteEntity(
    author = this.author,
    quote = this.quote,
    quote_id = this.quote_id
)


fun List<QuoteEntity>.toDomain() = map {
    it.toDomain()
}

fun List<QuoteDto>.toEntity() = map {
    it.toEntity()
}