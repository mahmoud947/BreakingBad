package com.example.breakingbad.peresentation.fragment.characters.view_model

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.domain.use_case.GetCharactersUseCase
import com.example.breakingbad.domain.use_case.RefreshCharactersUseCase
import com.example.breakingbad.peresentation.fragment.characters.CharacterFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val refreshCharactersUseCase: RefreshCharactersUseCase
) :
    ViewModel() {
    var listState: Parcelable? = null;
    private val _state: MutableLiveData<CharacterFragmentState> = MutableLiveData()
    val state: LiveData<CharacterFragmentState> get() = _state

    val oldCharacters: MutableLiveData<ArrayList<CharacterDto>> = MutableLiveData()

    init {
        refresh()
    }
    private fun refresh(){
        viewModelScope.launch {
            refreshCharactersUseCase().flowOn(Dispatchers.IO)
                .collect {

                }
        }
    }

    fun getAllCharacter() {
        viewModelScope.launch {
            refreshCharactersUseCase()
            getCharactersUseCase().flowOn(Dispatchers.IO)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _state.postValue(CharacterFragmentState.ShowError(message = resource.message))
                        }
                        is Resource.Loading -> {
                            _state.postValue(CharacterFragmentState.ShowLoading)
                        }
                        is Resource.Success -> {
                            resource.data?.collect {
                                _state.postValue(CharacterFragmentState.ShowData(characters = it))
                            }
                        }
                    }
                }
        }
    }

}