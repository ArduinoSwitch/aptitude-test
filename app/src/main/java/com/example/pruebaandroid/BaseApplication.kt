package com.example.pruebaandroid

import android.app.Application
import com.example.pruebaandroid.base.mainModule
import com.example.pruebaandroid.features.detail.detailModule
import com.example.pruebaandroid.features.transactions.transactionModule
import com.example.pruebaandroid.remote.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    mainModule,
                    apiModule,
                    transactionModule,
                    detailModule,
                )
            )
        }
    }
}