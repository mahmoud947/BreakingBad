package com.example.breakingbad.peresentation.fragment.characters

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: RepositoryImpl) :
    ViewModel() {
    var listState: Parcelable? = null;
    var characterList: MutableLiveData<ArrayList<CharacterDto>> = MutableLiveData()
    fun getAllCharacter() {
        viewModelScope.launch {
            val response: ArrayList<CharacterDto> = repository.getAllCharacters()
            characterList.value = response
        }
    }

}