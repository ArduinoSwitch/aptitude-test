package com.example.pruebaandroid.features.transactions.ui

import androidx.lifecycle.MutableLiveData
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
) : ViewModel() {

    private var transactionsList : List<TransactionModel>? = null
    private var ratesList : List<RatesModel>? = null
    val differentTransactionList = MutableLiveData<List<TransactionModel>>()

    init {
        viewModelScope.launch {
            fetchXml()
            transactionsList?.let {
                setUpDifferentTransactionList(it)
            }
        }
    }

    private suspend fun fetchXml() = withContext(Dispatchers.Default) {
        checkResult(ratesUseCase.invoke(Unit))?.let {
            it.body()?.let { list ->
                ratesList = list
            }
        }
        checkResult(transactionsUseCase.invoke(Unit))?.let {
            it.body()?.let { list ->
                transactionsList = list
            }
        }
    }

    private fun setUpDifferentTransactionList(list: List<TransactionModel>) {
        differentTransactionList.value = list.distinctBy { it.sku }
    }

    private fun <T> checkResult(data: Response<T>): Response<T>? {
        return if (data.isSuccessful) {
            if (data.body() != null) data else null
        } else {
            null
        }
    }
}