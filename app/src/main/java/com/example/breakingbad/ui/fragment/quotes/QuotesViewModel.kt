package com.example.breakingbad.ui.fragment.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.model.Quote
import com.example.breakingbad.repository.CharactersFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val response: CharactersFragmentRepository) :
    ViewModel() {
    var quotesList: MutableLiveData<ArrayList<Quote>> = MutableLiveData()

    fun getAllQuotes() {
        viewModelScope.launch {
            quotesList.value = response.getAllQuote()
        }
    }

}