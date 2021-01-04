package com.example.pruebaandroid.remote

import com.example.pruebaandroid.base.domain.RatesModel
import com.example.pruebaandroid.base.domain.TransactionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {
    @Headers("Accept: application/json")
    @GET("rates")
    suspend fun getRates(): Response<List<RatesModel>>

    @GET("transactions")
    suspend fun getTransactions(): Response<List<TransactionModel>>
}