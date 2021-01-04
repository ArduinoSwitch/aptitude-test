package com.example.pruebaandroid.base

import com.example.pruebaandroid.base.data.TransactionDataSourceImpl
import com.example.pruebaandroid.base.domain.RatesUseCase
import com.example.pruebaandroid.base.domain.TransactionDataSource
import com.example.pruebaandroid.base.domain.TransactionsUseCase
import com.example.pruebaandroid.base.ui.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var mainModule = module {
    viewModel { SharedViewModel() }
    factory<TransactionDataSource> { TransactionDataSourceImpl(get()) }
    factory { RatesUseCase(get()) }
    factory { TransactionsUseCase(get()) }
}