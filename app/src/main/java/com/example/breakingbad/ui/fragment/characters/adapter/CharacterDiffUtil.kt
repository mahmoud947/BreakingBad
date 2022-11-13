package com.example.breakingbad.ui.fragment.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto

class CharacterDiffUtil(
    private val oldList: ArrayList<CharacterDto>,
    private val newList: ArrayList<CharacterDto>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].char_id == newList[newItemPosition].char_id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}