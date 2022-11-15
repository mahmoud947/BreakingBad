package com.example.breakingbad.peresentation.fragment.quotes.view_model

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.core.network.Resource
import com.example.breakingbad.domain.use_case.GetQuotesUseCase
import com.example.breakingbad.domain.use_case.RefreshQuotesUseCase
import com.example.breakingbad.peresentation.fragment.quotes.QuotesFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val refreshQuotesUseCase: RefreshQuotesUseCase
) :
    ViewModel() {
    var listState: Parcelable? = null
    private val _state: MutableLiveData<QuotesFragmentState> = MutableLiveData()
    val state: LiveData<QuotesFragmentState> get() = _state

    init {
        refresh()
        getQuotes()
    }

    private fun refresh(){
        viewModelScope.launch {
            refreshQuotesUseCase().flowOn(Dispatchers.IO)
                .collect {

                }
        }
    }

    fun getQuotes() {
        viewModelScope.launch {
            getQuotesUseCase().flowOn(Dispatchers.IO)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _state.postValue(QuotesFragmentState.ShowError(resource.message))
                        }
                        is Resource.Loading -> {
                            _state.postValue(QuotesFragmentState.ShowLoading)
                        }
                        is Resource.Success -> {
                            resource.data?.collect {
                                _state.postValue(QuotesFragmentState.ShowData(it))
                            }

                        }
                    }
                }
        }
    }

}