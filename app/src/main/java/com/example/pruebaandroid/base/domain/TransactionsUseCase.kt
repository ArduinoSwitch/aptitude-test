package com.example.pruebaandroid.base.domain

import com.example.pruebaandroid.base.result.UseCaseSuspend
import retrofit2.Response

class TransactionsUseCase(
    private val transactionDataSource: TransactionDataSource
): UseCaseSuspend<Unit, Response<List<TransactionModel>>> {
    override suspend fun invoke(params: Unit): Response<List<TransactionModel>> {
        return transactionDataSource.getTransactions()
    }
}