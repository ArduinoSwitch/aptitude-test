package com.example.pruebaandroid.features.transactions

import com.example.pruebaandroid.base.domain.RatesUseCase
import com.example.pruebaandroid.base.domain.TransactionsUseCase
import com.example.pruebaandroid.base.ui.SharedViewModel
import com.example.pruebaandroid.features.transactions.ui.TransactionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var transactionModule = module {
    viewModel {
        (sharedViewModel: SharedViewModel) ->
        TransactionsViewModel(
            sharedViewModel,
            get<RatesUseCase>(),
            get<TransactionsUseCase>()
        )
    }
}