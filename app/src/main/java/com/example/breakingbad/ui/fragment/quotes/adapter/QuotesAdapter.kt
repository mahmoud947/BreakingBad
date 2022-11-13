package com.example.breakingbad.ui.fragment.quotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.ItemQuotesRowBinding
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto
import javax.inject.Inject

class QuotesAdapter @Inject constructor() : RecyclerView.Adapter<QuotesAdapter.MyViewHolder>() {
    private var oldList: ArrayList<QuoteDto> = ArrayList()
    private var myListRef: ArrayList<QuoteDto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.MyViewHolder =
        MyViewHolder(ItemQuotesRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: QuotesAdapter.MyViewHolder, position: Int) {
        val quote = oldList[position]
        holder.bind(quote)
    }

    override fun getItemCount() = oldList.size

    fun setData(newList: ArrayList<QuoteDto>) {
        val diffUtil = QuotesDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList.clear()
        oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MyViewHolder(private val binding: ItemQuotesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quotes: QuoteDto) {
            binding.tvItemQuoteQuotes.text = quotes.quote
            binding.tvItemAuthorQoutes.text = quotes.author
        }
    }
}