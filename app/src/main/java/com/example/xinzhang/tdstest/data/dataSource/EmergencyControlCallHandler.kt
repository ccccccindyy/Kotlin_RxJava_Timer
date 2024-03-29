package com.example.xinzhang.tdstest.data.dataSource

import com.example.xinzhang.tdstest.data.dataModel.Employee
import com.example.xinzhang.tdstest.data.dataSource.api.EmployeeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class EmergencyControlCallHandler {

    private var disposable: Disposable? = null

    private val employeeRepository  = EmployeeRepository()

    val loading = employeeRepository.loading

    fun resume(onNext: Consumer<List<Employee>> , onError: Consumer<Throwable>) {
        if(disposable == null || disposable!!.isDisposed) {
            disposable = employeeRepository.getRealTimeEmployeeInfo()
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError)
        }
    }

    fun stop() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
}