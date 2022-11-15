package com.example.breakingbad.characters_feature.peresentation.fragment.character_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.breakingbad.databinding.FragmentCharacterInfoBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater)
        coordinateMotion()
        val args: CharacterInfoFragmentArgs by navArgs()
        val character = args.character
        binding.character = character

        return binding.root
    }

    private fun coordinateMotion(){
        val appBarLayout:AppBarLayout = binding.appbarLayout
        val motionLayout:MotionLayout = binding.motionLayout

        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }
        appBarLayout.addOnOffsetChangedListener(listener)
    }

}