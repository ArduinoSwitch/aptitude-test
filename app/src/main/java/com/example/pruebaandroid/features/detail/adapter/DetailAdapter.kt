package com.example.pruebaandroid.features.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaandroid.R
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.databinding.ItemDetailBinding
import com.example.pruebaandroid.features.transactions.adapter.TransactionAdapter

class DetailAdapter : ListAdapter<TransactionModel, DetailAdapter.CustomViewHolder>(
    TransactionAdapter.DiffCallback()
) {
    inner class CustomViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransactionModel) {
            binding.data = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemDetailBinding>(
            layoutInflater,
            R.layout.item_detail,
            parent,
            false
        )
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}