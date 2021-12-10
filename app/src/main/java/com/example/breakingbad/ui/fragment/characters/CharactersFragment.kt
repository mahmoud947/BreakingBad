package com.example.breakingbad.ui.fragment.characters

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentCharactersBinding
import com.example.breakingbad.model.Character
import com.example.breakingbad.ui.fragment.characters.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(), CharacterAdapter.OnItemClickListener {
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: FragmentCharactersBinding

    @Inject
    lateinit var adapterChar: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentCharactersBinding.inflate(inflater, container, false)

        binding.rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterChar
        }
        viewModel.getAllCharacter()
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            adapterChar.setData(characters)
        }
        adapterChar.setOnClickInterFace(this)



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater);
        val searchItem = menu.findItem(R.id.toolbar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (!p0.toString().isEmpty() || p0.toString().equals("")) {
                    adapterChar.filter.filter(p0)
                }
                return false
            }


        })

    }

    override fun onItemClick(position: Int, character: Character, binding: CardView) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterInfoFragment(character.char_id,character.name)
        findNavController().navigate(action)
    }
}