package com.example.breakingbad.characters_feature.peresentation.fragment.character_info

import com.example.breakingbad.characters_feature.domain.model.CharacterModel

sealed class CharacterInfoFragmentState {
    object ShowLoading : CharacterInfoFragmentState()
    data class ShowError(val message: String?) : CharacterInfoFragmentState()
    data class ShowData(val characterModel: CharacterModel) : CharacterInfoFragmentState()
    object UpdatedSuccessfully:CharacterInfoFragmentState()
}
