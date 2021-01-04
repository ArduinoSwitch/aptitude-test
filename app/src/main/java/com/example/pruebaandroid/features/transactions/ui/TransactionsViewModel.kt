package com.example.pruebaandroid.features.transactions.ui

import androidx.lifecycle.*
import com.example.pruebaandroid.base.domain.RatesModel
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.base.result.UseCaseSuspend
import com.example.pruebaandroid.base.ui.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TransactionsViewModel(
    private val sharedViewModel: SharedViewModel,
    private val ratesUseCase: UseCaseSuspend<Unit, Response<List<RatesModel>>>,
    private val transactionsUseCase: UseCaseSuspend<Unit, Response<List<TransactionModel>>>
) : ViewModel() {

    val differentTransactionList = MutableLiveData<List<TransactionModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val emptyList: LiveData<Boolean> = differentTransactionList.map {
        it.isNullOrEmpty()
    }

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            isLoading.value = true
            fetchXml()
            isLoading.value = false
        }
    }

    private suspend fun fetchXml() = withContext(Dispatchers.Default) {
        checkResult(ratesUseCase.invoke(Unit))?.let {
            it.body()?.let { list ->
                sharedViewModel.ratesList = list
            }
        }
        checkResult(transactionsUseCase.invoke(Unit))?.let {
            it.body()?.let { list ->
                sharedViewModel.transactionsList = list
                setUpDifferentTransactionList(list)
            }
        }
    }

    private fun setUpDifferentTransactionList(list: List<TransactionModel>) {
        differentTransactionList.postValue(list.distinctBy { it.sku })
    }

    private fun <T> checkResult(data: Response<T>): Response<T>? {
        return if (data.isSuccessful) {
            if (data.body() != null) data else null
        } else {
            null
        }
    }
}