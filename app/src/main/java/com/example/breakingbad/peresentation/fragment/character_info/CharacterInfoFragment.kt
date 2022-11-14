package com.example.breakingbad.peresentation.fragment.character_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.breakingbad.databinding.FragmentCharacterInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater)
        val args: CharacterInfoFragmentArgs by navArgs()
        val character = args.character
        binding.character = character

        return binding.root
    }

}