package com.example.breakingbad.characters_feature.peresentation.fragment.favorite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.breakingbad.characters_feature.domain.model.CharacterModel

class FavoriteCharacterDiffUtil(
    private val oldList: ArrayList<CharacterModel>,
    private val newList: ArrayList<CharacterModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].char_id == newList[newItemPosition].char_id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}