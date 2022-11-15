package com.example.breakingbad.quotes_feature.peresentaion.freagment.quotes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.breakingbad.quotes_feature.domain.model.QuoteModel

class QuotesDiffUtil(
    private val oldList: ArrayList<QuoteModel>,
    private val newList: ArrayList<QuoteModel>,
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