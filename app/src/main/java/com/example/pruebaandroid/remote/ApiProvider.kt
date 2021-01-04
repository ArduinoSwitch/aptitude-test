package com.example.pruebaandroid.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider : KoinComponent {
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(HeaderInterceptor())
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    fun providerRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://quiet-stone-2094.herokuapp.com/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
}