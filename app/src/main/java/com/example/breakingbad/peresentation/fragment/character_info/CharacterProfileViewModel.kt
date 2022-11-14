package com.example.breakingbad.peresentation.fragment.character_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.data.repository.RepositoryImpl
import com.example.breakingbad.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterProfileViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    var character: MutableLiveData<ArrayList<CharacterDto>> = MutableLiveData()

    fun getCharacter(char_id: Int) {
        viewModelScope.launch {
            character.value = repository.getCharacterById(char_id)
        }
    }

}