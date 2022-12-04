package com.example.breakingbad.characters_feature.peresentation.fragment.favorite.view_model

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.characters_feature.domain.use_case.GetFavoriteCharactersUseCase
import com.example.breakingbad.characters_feature.peresentation.fragment.favorite.FavoriteCharacterFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetFavoriteCharactersUseCase,
) : ViewModel() {
    var listState: Parcelable? = null;
    private val _state: MutableLiveData<FavoriteCharacterFragmentState> = MutableLiveData()
    val state: LiveData<FavoriteCharacterFragmentState> get() = _state

    init {
        getAllCharacters()
    }

     fun getAllCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().flowOn(Dispatchers.IO)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _state.postValue(FavoriteCharacterFragmentState.ShowError(message = resource.message))
                        }
                        is Resource.Loading -> {
                            _state.postValue(FavoriteCharacterFragmentState.ShowLoading)
                        }
                        is Resource.Success -> {
                            resource.data?.collect {
                                _state.postValue(FavoriteCharacterFragmentState.ShowData(characters = it))
                            }
                        }
                    }
                }
        }
    }

}