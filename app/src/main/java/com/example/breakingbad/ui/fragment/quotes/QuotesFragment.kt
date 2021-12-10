package com.example.breakingbad.ui.fragment.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentQuotesBinding
import com.example.breakingbad.model.Quote
import com.example.breakingbad.ui.fragment.quotes.adapter.QuotesAdapter
import com.example.breakingbad.util.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_quotes.*
import javax.inject.Inject

@AndroidEntryPoint
class QuotesFragment : Fragment(R.layout.fragment_quotes) {
    private lateinit var binding: FragmentQuotesBinding
    private val viewModel: QuotesViewModel by viewModels()
    private var myList: ArrayList<Quote> = ArrayList()

    private var fragmentFirstBinding: FragmentQuotesBinding? = null

    @Inject
    lateinit var quotesAdapter: QuotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
        binding.rvQuotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvQuotes.adapter = quotesAdapter
        if (Constant.quoteList.isEmpty()) {
            viewModel.getAllQuotes()
            viewModel.quotesList.observe(viewLifecycleOwner, Observer { quotes ->
                Constant.quoteList = quotes
                quotesAdapter.setData(Constant.quoteList)
            })
        } else {
            quotesAdapter.setData(Constant.quoteList)
        }


        return binding.root
    }



}