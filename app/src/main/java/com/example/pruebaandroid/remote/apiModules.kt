package com.example.pruebaandroid.remote

import org.koin.dsl.module

internal typealias ApiFactory = () -> Api

var apiModule = module {
    single { ApiProvider.provideApi(get()) }
    single { ApiProvider.providerRetrofit() }
    factory<ApiFactory> { { get() } }
}