package com.example.breakingbad.ui.fragment.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.model.Character
import com.example.breakingbad.repository.CharactersFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: CharactersFragmentRepository) :
    ViewModel() {
        var characterList:MutableLiveData<ArrayList<Character>> = MutableLiveData()

    fun getAllCharacter() {
        viewModelScope.launch {
            val response: ArrayList<Character> = repository.getAllCharacters()
            characterList.value = response
        }
    }

}