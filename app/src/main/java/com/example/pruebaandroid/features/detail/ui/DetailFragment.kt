package com.example.pruebaandroid.features.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaandroid.R
import com.example.pruebaandroid.base.ui.SharedViewModel
import com.example.pruebaandroid.databinding.FragmentDetailBinding
import com.example.pruebaandroid.features.detail.adapter.DetailAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val safeArgs: DetailFragmentArgs by navArgs()
        val transactionId = safeArgs.data
        viewModel = getViewModel { parametersOf(transactionId, sharedViewModel) }
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backArrow = view.findViewById<ImageView>(R.id.back_arrow)
        backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDetail)
        setRecyclerView(recyclerView)
    }

    private fun setRecyclerView(recyclerView: RecyclerView) {
        val layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        val adapter = DetailAdapter()
        with(recyclerView) {
            this.layoutManager = layoutManager
            this.adapter = adapter
            subscribeItemUI(adapter)
        }
    }

    private fun subscribeItemUI(adapter: DetailAdapter) {
        viewModel.transactionList.observe(viewLifecycleOwner, {
            it.let(adapter::submitList)
        })
    }
}