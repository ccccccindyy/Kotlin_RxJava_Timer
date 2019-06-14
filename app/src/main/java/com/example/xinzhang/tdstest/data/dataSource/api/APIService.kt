package com.example.xinzhang.tdstest.data.dataSource.api

import com.example.xinzhang.tdstest.data.dataModel.Employee
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val BASE_URL: String = "http://dummy.restapiexample.com/api/v1/"
const val EMPLOYEE: String = "employees"

interface APIService {

    @retrofit2.http.GET(EMPLOYEE)
    fun employees(): io.reactivex.Observable<List<Employee>>


    companion object Factory {

        private var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()

        fun create(): APIService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}