package com.example.breakingbad.quotes_feature.peresentaion.freagment.quotes

import com.example.breakingbad.quotes_feature.domain.model.QuoteModel

sealed class QuotesFragmentState {
    object ShowLoading : QuotesFragmentState()
    data class ShowError(val message: String?) : QuotesFragmentState()
    data class ShowData(val characters: List<QuoteModel>) : QuotesFragmentState()
}
