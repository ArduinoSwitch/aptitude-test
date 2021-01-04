package com.example.pruebaandroid.features.transactions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaandroid.R
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.databinding.ItemTransactionBinding

class TransactionAdapter(
    private val onItemClick: ((TransactionModel) -> Unit)? = null
): ListAdapter<TransactionModel,TransactionAdapter.TransactionViewHolder>(DiffCallback()) {

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(onItemClick: ((TransactionModel) -> Unit)?, item: TransactionModel) {
            if (onItemClick != null) {
                binding.data = item
                binding.root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTransactionBinding>(
            layoutInflater,
            R.layout.item_transaction,
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(onItemClick, getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TransactionModel>() {
        override fun areItemsTheSame(
            oldItem: TransactionModel,
            newItem: TransactionModel): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: TransactionModel,
            newItem: TransactionModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}