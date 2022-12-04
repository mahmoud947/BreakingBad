package com.example.breakingbad.characters_feature.peresentation.fragment.character_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.breakingbad.characters_feature.peresentation.fragment.character_info.view_model.CharacterInfoViewModel
import com.example.breakingbad.databinding.FragmentCharacterInfoBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding
    private val viewModel: CharacterInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater)
        coordinateMotion()
        val args: CharacterInfoFragmentArgs by navArgs()
        val character = args.character
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getCharacterInfo(character.char_id)
        viewModel.state.observe(viewLifecycleOwner){state->
            when(state){
                is CharacterInfoFragmentState.ShowData->{
                    binding.character = state.characterModel
                    binding.executePendingBindings()
                }
                is CharacterInfoFragmentState.ShowLoading->{
                    Toast.makeText(requireContext(),"Loading",Toast.LENGTH_SHORT).show()
                }
                is CharacterInfoFragmentState.ShowError->{
                    Toast.makeText(requireContext(),state.message,Toast.LENGTH_SHORT).show()
                }
                is CharacterInfoFragmentState.UpdatedSuccessfully->{
                Toast.makeText(requireContext(),"Character add to Favorite successfully",Toast.LENGTH_SHORT).show()
                }

            }
        }


        return binding.root
    }

    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout = binding.appbarLayout
        val motionLayout: MotionLayout = binding.motionLayout

        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }
        appBarLayout.addOnOffsetChangedListener(listener)
    }

}