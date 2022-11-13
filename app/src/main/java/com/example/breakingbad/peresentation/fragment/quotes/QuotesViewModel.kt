package com.example.breakingbad.peresentation.fragment.quotes

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto
import com.example.breakingbad.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val response: RepositoryImpl) :
    ViewModel() {
    var listState:Parcelable?=null
    var quotesList: MutableLiveData<ArrayList<QuoteDto>> = MutableLiveData()

    fun getAllQuotes() {
        viewModelScope.launch {
            quotesList.postValue(response.getAllQuote())
        }
    }

}