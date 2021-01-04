package com.example.pruebaandroid.features.transactions.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaandroid.base.domain.RatesModel
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.base.result.UseCaseSuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TransactionsViewModel(
    private val ratesUseCase: UseCaseSuspend<Unit, Response<List<RatesModel>>>,
    private val transactionsUseCase: UseCaseSuspend<Unit, Response<List<TransactionModel>>>
): ViewModel() {

    init {
        viewModelScope.launch {
            fetchXml()
        }
    }

    private suspend fun fetchXml() = withContext(Dispatchers.Default) {
        val resultRates = ratesUseCase.invoke(Unit)
        val resultTransactions = transactionsUseCase.invoke(Unit)
        if (resultTransactions.isSuccessful) {
            println(resultTransactions.body()?.last())
        }
        // TODO Check if request is successful
    }
}