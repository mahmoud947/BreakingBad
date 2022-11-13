package com.example.breakingbad.peresentation.fragment.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentQuotesBinding
import com.example.breakingbad.data.data_source.remote.dto.QuoteDto
import com.example.breakingbad.peresentation.fragment.quotes.adapter.QuotesAdapter
import com.example.breakingbad.util.Constant
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuotesFragment : Fragment(R.layout.fragment_quotes) {
    private lateinit var binding: FragmentQuotesBinding
    private val viewModel: QuotesViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private var myList: ArrayList<QuoteDto> = ArrayList()
    private var fragmentFirstBinding: FragmentQuotesBinding? = null


    @Inject
    lateinit var quotesAdapter: QuotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
        binding.rvQuotes.apply {
            layoutManager= LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter=quotesAdapter
        }
        if (Constant.quoteList.isEmpty()) {
            viewModel.getAllQuotes()
            viewModel.quotesList.observe(viewLifecycleOwner, Observer { quotes ->
                Constant.quoteList = quotes
                quotesAdapter.setData(Constant.quoteList)
            })
        } else {
            quotesAdapter.setData(Constant.quoteList)
            viewModel.getAllQuotes()
            viewModel.quotesList.observe(viewLifecycleOwner, Observer { quotes->
                quotesAdapter.setData(quotes)
            })
        }

        if (viewModel.listState!=null){
            binding.rvQuotes.layoutManager?.onRestoreInstanceState(viewModel.listState)
            viewModel.listState=null
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listState=binding.rvQuotes.layoutManager?.onSaveInstanceState()
    }


}