package com.example.xinzhang.tdstest.data.dataSource.api

import com.example.xinzhang.tdstest.data.dataModel.Employee
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


interface APIService {

    @retrofit2.http.GET("employees")
    fun employees(): io.reactivex.Observable<List<Employee>>


    companion object Factory {

        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        fun create(): APIService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}