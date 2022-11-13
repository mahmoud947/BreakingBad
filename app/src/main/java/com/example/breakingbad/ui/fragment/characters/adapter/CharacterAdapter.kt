package com.example.breakingbad.ui.fragment.characters.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ItemCharacterRowBinding
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import javax.inject.Inject

class CharacterAdapter @Inject constructor() :
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>(), Filterable {
    private var oldList: ArrayList<CharacterDto> = ArrayList()
    private var myListRef: ArrayList<CharacterDto> = ArrayList()
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        return MyViewHolder(ItemCharacterRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val myList = oldList[position]
        holder.bind(myList)
    }

    override fun getItemCount(): Int = oldList.size

    fun setData(newList: ArrayList<CharacterDto>) {
        val diffUtil = CharacterDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        if (myListRef.isEmpty())
            myListRef = newList
        oldList.clear()
        oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnClickInterFace(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class MyViewHolder(private val binding: ItemCharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(character: CharacterDto) {
            binding.apply {
                ivItem.load(character.img) {
                    placeholder(R.drawable.walter_logo)
                    crossfade(true)
                    crossfade(400)

                }
                tvItemCharName.text = character.name
                tvItemCharNicName.text = character.nickname
            }
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var position = bindingAdapterPosition
            var character: CharacterDto = oldList[position]
            listener.onItemClick(position, character, binding.root)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, character: CharacterDto, binding: CardView)
    }

    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filteredList: ArrayList<CharacterDto> = ArrayList()
                if (p0.toString().isEmpty() || p0.toString().equals("")) {
                    filteredList = myListRef
                    Log.i("filters", myListRef.size.toString())
                } else {
                    Log.i("filters", myListRef.size.toString())
                    for (item in myListRef) {
                        if (item.name.lowercase().contains(p0.toString().lowercase())) {
                            filteredList.add(item)
                        }
                    }
                }

                val filterResult: FilterResults = FilterResults()
                filterResult.values = filteredList
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                if (p1 != null) {
                    setData(p1.values as ArrayList<CharacterDto>)
                }
            }


        }
    }


}