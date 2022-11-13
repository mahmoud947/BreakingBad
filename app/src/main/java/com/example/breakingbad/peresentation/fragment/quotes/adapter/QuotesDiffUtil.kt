package com.example.breakingbad.peresentation.fragment.quotes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto

class QuotesDiffUtil(
    private val oldList: ArrayList<QuoteDto>,
    private val newList: ArrayList<QuoteDto>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].quote_id == newList[newItemPosition].quote_id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}