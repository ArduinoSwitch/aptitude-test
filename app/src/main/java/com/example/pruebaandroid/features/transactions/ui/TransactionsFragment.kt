package com.example.pruebaandroid.features.transactions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pruebaandroid.R
import com.example.pruebaandroid.databinding.FragmentTransactionsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionsFragment: Fragment() {

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
            false)
        binding.viewModel = viewModel
        setUpOnClickListener(binding.root)
        return binding.root
    }

    private fun setUpOnClickListener(root: View?) {
        root?.let {
            val text = it.findViewById<TextView>(R.id.test)
            text.setOnClickListener {
                findNavController().navigate(R.id.nav_to_detail)
            }
        }
    }
}