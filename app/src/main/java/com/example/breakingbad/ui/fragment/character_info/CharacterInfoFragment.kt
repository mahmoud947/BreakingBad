package com.example.breakingbad.ui.fragment.character_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentCharacterInfoBinding
import com.example.breakingbad.data.data_source.remote.dto.CharacterDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {
    lateinit var binding: FragmentCharacterInfoBinding
    private val args: CharacterInfoFragmentArgs by navArgs()
    private lateinit var characters: ArrayList<CharacterDto>
    private val viewModel: CharacterProfileViewModel by viewModels()

    @SuppressLint("CheckResult", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        viewModel.getCharacter(args.charId)
        viewModel.character.observe(viewLifecycleOwner) { char ->
            characters = char
            if (characters != null) {
                val character = characters[0]

                Glide.with(requireContext())
                    .load(character.img).apply {
                        thumbnail(Glide.with(requireContext()).load(R.drawable.loading))
                        useAnimationPool
                        circleCrop()
                        into(binding.ivCharacterProfile)
                    }
                Glide.with(requireContext())
                    .load(character.img).apply {
                        thumbnail(Glide.with(requireContext()).load(R.drawable.loading))
                        useAnimationPool
                        centerCrop()
                        into(binding.ivCharacterCover)
                    }

                binding.tvCharacterBirthday.text = character.birthday
                for (appearance in character.appearance) {
                    binding.tvCharacterInfoApperance.append("\n season " + appearance + "\n")
                }

                binding.tvCharacterInfoNickname.text = character.nickname
                for (occupation in character.occupation) {
                    binding.tvCharacterInfoOccupation.append("\n${
                        character.occupation.indexOf(occupation) + 1
                    }- " + occupation + "\n")
                }

                binding.tvCharacterInfoPortrayed.text = character.portrayed
                binding.tvCharacterName.text = character.name
                binding.tvCharacterNickname.text = "( ${character.nickname} )"
                binding.tvCharacterInfoNickname.text = character.nickname
                if (character.status != "Alive") {
                    binding.tvCharacterInfoStatus.setTextColor(resources.getColor(R.color.read,null))
                    binding.tvCharacterInfoStatus.text = character.status
                } else {
                    binding.tvCharacterInfoStatus.text = character.status
                }
            }

        }


        return binding.root
    }

}