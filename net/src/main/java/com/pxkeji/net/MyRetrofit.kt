package com.pxkeji.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



fun createRetrofit(
        baseUrl: String, // 一定要以斜线结尾 例：http://api.scedumedia.com/
        connectTimeout: Long,
        readTimeout: Long,
        writeTimeout: Long,
        isDebug: Boolean
) : Retrofit {



    val loggingInterceptor = HttpLoggingInterceptor{
        if (isDebug) Log.w("RetrofitLog", it)
    }

    loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

    val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .build()

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit
}