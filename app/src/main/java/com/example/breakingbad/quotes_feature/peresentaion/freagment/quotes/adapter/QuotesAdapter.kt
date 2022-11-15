package com.example.breakingbad.quotes_feature.peresentaion.freagment.quotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.ItemQuotesRowBinding
import com.example.breakingbad.quotes_feature.domain.model.QuoteModel
import javax.inject.Inject

class QuotesAdapter @Inject constructor() : RecyclerView.Adapter<QuotesAdapter.MyViewHolder>() {
    private var oldList: ArrayList<QuoteModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemQuotesRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quote = oldList[position]
        holder.bind(quote)
    }

    override fun getItemCount() = oldList.size

    fun setData(newList: ArrayList<QuoteModel>) {
        val diffUtil = QuotesDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList.clear()
        oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MyViewHolder(private val binding: ItemQuotesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quotes: QuoteModel) {
            binding.quote = quotes
            binding.executePendingBindings()
        }
    }
}