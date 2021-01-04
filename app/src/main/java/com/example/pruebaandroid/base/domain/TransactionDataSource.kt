package com.example.pruebaandroid.base.domain

import retrofit2.Response

interface TransactionDataSource {
    suspend fun getRates(): Response<List<RatesModel>>
    suspend fun getTransactions(): Response<List<TransactionModel>>
}