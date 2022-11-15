package com.example.breakingbad.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {
    @TypeConverter
    fun listOfStringFromString(string: String): List<String> {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun listOfStringToJson(strings: List<String>): String {
        return Gson().toJson(strings)
    }

    @TypeConverter
    fun listOfIntFromString(string: String): List<Int> {
        val listType: Type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun listOfIntToJson(ints: List<Int>): String {
        return Gson().toJson(ints)
    }
}