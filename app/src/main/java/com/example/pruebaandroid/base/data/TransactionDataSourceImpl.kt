package com.example.pruebaandroid.base.data

import com.example.pruebaandroid.base.domain.RatesModel
import com.example.pruebaandroid.base.domain.TransactionDataSource
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.remote.Api
import retrofit2.Response
import java.lang.Exception

class TransactionDataSourceImpl(
    private val api: Api
): TransactionDataSource {
    override suspend fun getRates(): Response<List<RatesModel>> {
        return try {
            api.getRates()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.success(emptyList())
        }
    }

    override suspend fun getTransactions(): Response<List<TransactionModel>> {
        return try {
            api.getTransactions()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.success(emptyList())
        }
    }
}