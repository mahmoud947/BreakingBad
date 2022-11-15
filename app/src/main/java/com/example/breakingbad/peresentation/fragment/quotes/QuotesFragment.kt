package com.example.breakingbad.peresentation.fragment.quotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentQuotesBinding
import com.example.breakingbad.domain.model.QuoteModel
import com.example.breakingbad.peresentation.fragment.quotes.adapter.QuotesAdapter
import com.example.breakingbad.peresentation.fragment.quotes.view_model.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "QuotesFragment"
@AndroidEntryPoint
class QuotesFragment : Fragment(R.layout.fragment_quotes) {
    private lateinit var binding: FragmentQuotesBinding
    private val viewModel: QuotesViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    @Inject
    lateinit var quotesAdapter: QuotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuotesBinding.inflate(inflater)
        binding.rvQuotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = quotesAdapter
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is QuotesFragmentState.ShowData -> {
                    Log.d(TAG, "onCreateView: ${state.characters}")
                    val characters = ArrayList<QuoteModel>()
                    characters.addAll(state.characters)
                    quotesAdapter.setData(characters)
                }
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listState = binding.rvQuotes.layoutManager?.onSaveInstanceState()
    }


}