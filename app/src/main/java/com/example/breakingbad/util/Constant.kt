package com.example.breakingbad.util

import com.example.breakingbad.model.Character
import com.example.breakingbad.model.Quote

class Constant {
    companion object{
        const val BASE_URL="https://www.breakingbadapi.com/api/"
        const val SPLASH_SCREEN_TIME_OUT=3500
        var quoteList: ArrayList<Quote> = ArrayList()
        var characterList: ArrayList<Character> = ArrayList()
    }
}