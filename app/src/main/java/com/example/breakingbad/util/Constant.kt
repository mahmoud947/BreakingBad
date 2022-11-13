package com.example.breakingbad.util

import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto

class Constant {
    companion object{
        const val BASE_URL="https://www.breakingbadapi.com/api/"
        const val SPLASH_SCREEN_TIME_OUT=3500
        var quoteList: ArrayList<QuoteDto> = ArrayList()
        var characterList: ArrayList<CharacterDto> = ArrayList()
    }
}