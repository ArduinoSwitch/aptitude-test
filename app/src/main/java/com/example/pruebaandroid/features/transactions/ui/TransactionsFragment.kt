package com.example.pruebaandroid.features.transactions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaandroid.R
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.databinding.FragmentTransactionsBinding
import com.example.pruebaandroid.features.transactions.adapter.TransactionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionsFragment : Fragment() {

    private val viewModel: TransactionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTransactionsBinding>(
            inflater,
            R.layout.fragment_transactions,
            container,
            false
        )
        binding.viewModel = viewModel
        setRecyclerView(binding.recyclerView)
        return binding.root
    }

    private fun setRecyclerView(recyclerView: RecyclerView) {
        val adapter = getMyTransactionListAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        with(recyclerView) {
            this.layoutManager = layoutManager
            this.adapter = adapter
            subscribeItemUI(adapter)
        }
    }

    private fun subscribeItemUI(adapter: TransactionAdapter) {
        viewModel.differentTransactionList.observe(this.viewLifecycleOwner, {
            it.let(adapter::submitList)
        })
    }

    private fun getMyTransactionListAdapter() = TransactionAdapter(::setOnClickListener)

    private fun setOnClickListener(item: TransactionModel) {
        findNavController().navigate(R.id.nav_to_detail)
    }
}