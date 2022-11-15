package com.example.breakingbad.characters_feature.data.mapper

import com.example.breakingbad.characters_feature.data.data_source.local.entities.CharacterEntity
import com.example.breakingbad.characters_feature.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.characters_feature.domain.model.CharacterModel

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


