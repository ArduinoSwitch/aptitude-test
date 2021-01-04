package com.example.pruebaandroid.base.ui

import androidx.lifecycle.ViewModel
import com.example.pruebaandroid.base.domain.RatesModel
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.features.transactions.adapter.TransactionAdapter

class SharedViewModel: ViewModel() {
    var transactionsList : List<TransactionModel>? = null
    var ratesList : List<RatesModel>? = null
}