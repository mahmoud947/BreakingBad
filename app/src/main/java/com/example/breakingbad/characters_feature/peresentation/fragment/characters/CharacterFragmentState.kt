package com.example.breakingbad.characters_feature.peresentation.fragment.characters

import com.example.breakingbad.characters_feature.domain.model.CharacterModel

sealed class CharacterFragmentState {
    object ShowLoading : CharacterFragmentState()
    data class ShowError(val message: String?) : CharacterFragmentState()
    data class ShowData(val characters: List<CharacterModel>) : CharacterFragmentState()
}
