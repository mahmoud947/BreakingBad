package com.example.breakingbad.characters_feature.peresentation.fragment.characters

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import com.example.breakingbad.characters_feature.peresentation.fragment.characters.adapter.CharacterAdapter
import com.example.breakingbad.characters_feature.peresentation.fragment.characters.view_model.CharactersViewModel
import com.example.breakingbad.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding: FragmentCharactersBinding
    lateinit var adapterChar: CharacterAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentCharactersBinding.inflate(inflater)
        navController = findNavController()
        adapterChar = CharacterAdapter(
            CharacterAdapter.CharacterOnClickListener {
                navController.navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterInfoFragment(
                        it,
                        it.name
                    )
                )
            }
        )

        binding.rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterChar
        }
        viewModel.getAllCharacters()

        viewModel.listState?.let {
            binding.rvCharacters.layoutManager?.onRestoreInstanceState(it)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CharacterFragmentState.ShowData -> {
                    val characters = ArrayList<CharacterModel>()

                    characters.addAll(state.characters)
                    adapterChar.setData(characters)
                }
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listState = binding.rvCharacters.layoutManager?.onSaveInstanceState()
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


}