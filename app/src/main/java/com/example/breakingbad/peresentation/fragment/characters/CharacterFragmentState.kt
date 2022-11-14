package com.example.breakingbad.peresentation.fragment.characters

import com.example.breakingbad.domain.model.CharacterModel

sealed class CharacterFragmentState {
    object ShowLoading : CharacterFragmentState()
    data class ShowError(val message: String?) : CharacterFragmentState()
    data class ShowData(val characters: List<CharacterModel>) : CharacterFragmentState()
}
