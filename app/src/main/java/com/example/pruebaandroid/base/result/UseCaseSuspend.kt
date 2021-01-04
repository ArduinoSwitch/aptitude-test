package com.example.pruebaandroid.base.result

interface UseCaseSuspend<Params, Return> {
    suspend operator fun invoke(params: Params): Return
}