package com.example.breakingbad.ui.fragment.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.breakingbad.model.Character

class CharacterDiffUtil(
    private val oldList: ArrayList<Character>,
    private val newList: ArrayList<Character>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].char_id == newList[newItemPosition].char_id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList != newList
}