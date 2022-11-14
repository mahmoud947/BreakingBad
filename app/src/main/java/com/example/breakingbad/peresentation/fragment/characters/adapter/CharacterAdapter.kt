package com.example.breakingbad.peresentation.fragment.characters.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.ItemCharacterRowBinding
import com.example.breakingbad.domain.model.CharacterModel
import javax.inject.Inject

class CharacterAdapter (
private val onClickListener: CharacterOnClickListener
) :
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>(), Filterable {
    private var oldList: ArrayList<CharacterModel> = ArrayList()
    private var myListRef: ArrayList<CharacterModel> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        return MyViewHolder(
            ItemCharacterRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val characterModel = oldList[position]
        holder.bind(characterModel)
    }

    override fun getItemCount(): Int = oldList.size

    fun setData(newList: ArrayList<CharacterModel>) {
        val diffUtil = CharacterDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        if (myListRef.isEmpty())
            myListRef = newList
        oldList.clear()
        oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class MyViewHolder(private val binding: ItemCharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterModel) {
            binding.character = character
            binding.executePendingBindings()
            binding.clickListener = onClickListener
        }
    }

    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filteredList: ArrayList<CharacterModel> = ArrayList()
                if (p0.toString().isEmpty() || p0.toString() == "") {
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
                    setData(p1.values as ArrayList<CharacterModel>)
                }
            }
        }
    }

    class CharacterOnClickListener(val clickListener: ((character: CharacterModel) -> Unit)) {
        fun onClick(character: CharacterModel) {
            clickListener(character)
        }
    }

}