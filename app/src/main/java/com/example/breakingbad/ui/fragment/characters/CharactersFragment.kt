package com.example.breakingbad.ui.fragment.characters

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentCharactersBinding
import com.example.breakingbad.model.Character
import com.example.breakingbad.ui.fragment.characters.adapter.CharacterAdapter
import com.example.breakingbad.util.Constant
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(), CharacterAdapter.OnItemClickListener {
//    private val navViewModle:CharactersViewModel by navGraphViewModels(R.id.nav_graph) {
//        SavedStateViewModelFactory(requireActivity().application, requireParentFragment())
//    }
    private val viewModel: CharactersViewModel  by hiltNavGraphViewModels(R.id.nav_graph)
    private var _binding: FragmentCharactersBinding?=null
    private val binding get() = _binding!!
    private val myList:ArrayList<Character> = ArrayList()

    @Inject
    lateinit var adapterChar: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        binding.rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterChar
        }
        if (Constant.characterList.isEmpty()){
            viewModel.getAllCharacter()
            viewModel.characterList.observe(viewLifecycleOwner, Observer {  characters ->
                Constant.characterList=characters
                adapterChar.setData(Constant.characterList)
            })
            adapterChar.setOnClickInterFace(this)
        }else{
            adapterChar.setData(Constant.characterList)
            viewModel.getAllCharacter()
            viewModel.characterList.observe(viewLifecycleOwner, Observer { characters->
                adapterChar.setData(characters)
            })
            adapterChar.setOnClickInterFace(this)
        }


        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listState=binding.rvCharacters.layoutManager?.onSaveInstanceState()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        _binding=null
//    }

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

        if (viewModel.listState!=null){
            binding.rvCharacters.layoutManager?.onRestoreInstanceState(viewModel.listState)
            viewModel.listState=null
        }


    }

    override fun onItemClick(position: Int, character: Character, binding: CardView) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterInfoFragment(character.char_id,character.name)
        findNavController().navigate(action)
    }


}