package com.icehouse.pokeapp.data.network

import android.content.Context
import com.icehouse.pokeapp.BuildConfig
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.utils.NetworkUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiServices(private val context: Context) {

    fun create(): ApiInterface {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor {
                //check if network is available. If not throw an exception
                if(!NetworkUtils.isOnline(context)) {
                    throw IOException(context.getString(R.string.internet_error_title))
                } else {
                    return@addInterceptor it.proceed(it.request().newBuilder().build())
                }
            }.build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.SERVER_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}