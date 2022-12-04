package com.example.breakingbad.characters_feature.peresentation.fragment.character_info.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import com.example.breakingbad.characters_feature.domain.use_case.GetCharacterInfoUseCase
import com.example.breakingbad.characters_feature.domain.use_case.AddCharacterToFavoriteUseCase
import com.example.breakingbad.characters_feature.peresentation.fragment.character_info.CharacterInfoFragmentState
import com.example.breakingbad.core.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val updateCharacterUseCase: AddCharacterToFavoriteUseCase,
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
) : ViewModel() {
    private val _state: MutableLiveData<CharacterInfoFragmentState> = MutableLiveData()
    val state: LiveData<CharacterInfoFragmentState> get() = _state

    fun addCharacterToFavorite(characterModel: CharacterModel) {
        viewModelScope.launch {
            updateCharacterUseCase(characterModel = characterModel).flowOn(Dispatchers.IO)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _state.postValue(CharacterInfoFragmentState.ShowError(message = resource.message))
                        }
                        is Resource.Loading -> {
                            _state.postValue(CharacterInfoFragmentState.ShowLoading)
                        }
                        is Resource.Success -> {
                            _state.postValue(CharacterInfoFragmentState.UpdatedSuccessfully)
                        }
                    }
                }
        }
    }

    fun getCharacterInfo(charId: Int) {
        viewModelScope.launch {
            getCharacterInfoUseCase(charId).flowOn(Dispatchers.IO)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _state.postValue(CharacterInfoFragmentState.ShowError(message = resource.message))
                        }
                        is Resource.Loading -> {
                            _state.postValue(CharacterInfoFragmentState.ShowLoading)
                        }
                        is Resource.Success -> {
                            resource.data?.collect {
                                _state.postValue(CharacterInfoFragmentState.ShowData(characterModel = it))
                            }
                        }
                    }
                }
        }
    }

}