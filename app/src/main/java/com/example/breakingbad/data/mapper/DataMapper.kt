package com.example.breakingbad.data.mapper

import com.example.breakingbad.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.data.data_source.local.entities.QuoteEntity
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto
import com.example.breakingbad.domain.model.CharacterModel
import com.example.breakingbad.domain.model.QuoteModel

fun CharacterDto.toEntity(): CharacterEntity = CharacterEntity(
    appearance = this.appearance,
    better_call_saul_appearance = this.better_call_saul_appearance,
    birthday = this.birthday,
    category = this.category,
    char_id = this.char_id,
    img = this.img,
    name = this.name,
    nickname = this.nickname,
    occupation = this.occupation,
    portrayed = this.portrayed,
    status = this.status
)

fun CharacterEntity.toDomain(): CharacterModel = CharacterModel(
    appearance = this.appearance,
    better_call_saul_appearance = this.better_call_saul_appearance,
    birthday = this.birthday,
    category = this.category,
    char_id = this.char_id,
    img = this.img,
    name = this.name,
    nickname = this.nickname,
    occupation = this.occupation,
    portrayed = this.portrayed,
    status = this.status
)

fun CharacterModel.toEntity(): CharacterEntity = CharacterEntity(
    appearance = this.appearance,
    better_call_saul_appearance = this.better_call_saul_appearance,
    birthday = this.birthday,
    category = this.category,
    char_id = this.char_id,
    img = this.img,
    name = this.name,
    nickname = this.nickname,
    occupation = this.occupation,
    portrayed = this.portrayed,
    status = this.status
)

@JvmName("toDomainCharacterEntity")
fun List<CharacterEntity>.toDomain() = map {
    it.toDomain()
}
@JvmName("toEntityCharacterDto")
fun List<CharacterDto>.toEntity() = map {
    it.toEntity()
}


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