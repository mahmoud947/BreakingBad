package com.example.breakingbad.characters_feature.peresentation.fragment.favorite

import com.example.breakingbad.characters_feature.domain.model.CharacterModel

sealed class FavoriteCharacterFragmentState {
    object ShowLoading : FavoriteCharacterFragmentState()
    data class ShowError(val message: String?) : FavoriteCharacterFragmentState()
    data class ShowData(val characters: List<CharacterModel>) : FavoriteCharacterFragmentState()
}
