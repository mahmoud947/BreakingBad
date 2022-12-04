package com.example.breakingbad.characters_feature.peresentation.fragment.favorite

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
import com.example.breakingbad.characters_feature.peresentation.fragment.favorite.adapter.FavoriteCharacterAdapter
import com.example.breakingbad.characters_feature.peresentation.fragment.favorite.view_model.FavoriteCharactersViewModel
import com.example.breakingbad.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteCharactersViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding: FragmentFavoriteBinding
    lateinit var adapterChar: FavoriteCharacterAdapter
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentFavoriteBinding.inflate(inflater)
        navController = findNavController()
        adapterChar = FavoriteCharacterAdapter(
            CharacterAdapter.CharacterOnClickListener {
                navController.navigate(
                    FavoriteFragmentDirections.actionDeathFragmentToCharacterInfoFragment(
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
                is FavoriteCharacterFragmentState.ShowData -> {
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