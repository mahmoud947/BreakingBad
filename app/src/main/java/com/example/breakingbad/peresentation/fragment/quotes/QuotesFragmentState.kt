package com.example.breakingbad.peresentation.fragment.quotes

import com.example.breakingbad.domain.model.QuoteModel

sealed class QuotesFragmentState {
    object ShowLoading : QuotesFragmentState()
    data class ShowError(val message: String?) : QuotesFragmentState()
    data class ShowData(val characters: List<QuoteModel>) : QuotesFragmentState()
}
