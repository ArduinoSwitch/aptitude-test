package com.example.pruebaandroid.base.data

import com.example.pruebaandroid.base.domain.RatesModel
import com.example.pruebaandroid.base.domain.TransactionDataSource
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.remote.Api
import retrofit2.Response

class TransactionDataSourceImpl(
    private val api: Api
): TransactionDataSource {
    override suspend fun getRates(): Response<List<RatesModel>> {
        return api.getRates()
    }

    override suspend fun getTransactions(): Response<List<TransactionModel>> {
        return api.getTransactions()
    }
}