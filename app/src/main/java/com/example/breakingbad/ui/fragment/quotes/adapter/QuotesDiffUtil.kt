package com.example.breakingbad.ui.fragment.quotes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.breakingbad.model.Quote

class QuotesDiffUtil(
    private val oldList: ArrayList<Quote>,
    private val newList: ArrayList<Quote>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].quote_id == newList[newItemPosition].quote_id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList == newList
}