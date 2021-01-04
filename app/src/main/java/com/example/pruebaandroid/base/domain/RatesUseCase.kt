package com.example.pruebaandroid.base.domain

import com.example.pruebaandroid.base.result.UseCaseSuspend
import retrofit2.Response

class RatesUseCase(
    private val transactionDataSource: TransactionDataSource,
): UseCaseSuspend<Unit, Response<List<RatesModel>>> {
    override suspend fun invoke(params: Unit): Response<List<RatesModel>> {
        return transactionDataSource.getRates()
    }
}