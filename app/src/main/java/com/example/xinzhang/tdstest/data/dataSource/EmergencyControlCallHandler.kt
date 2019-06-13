package com.example.xinzhang.tdstest.data.dataSource

import com.example.xinzhang.tdstest.data.dataModel.Employee
import com.example.xinzhang.tdstest.data.dataSource.api.EmployeeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class EmergencyControlCallHandler {

    private var disposable: Disposable? = null

    fun resume(onNext: Consumer<List<Employee>>) {
        if(disposable == null || disposable!!.isDisposed) {
            println("resumed")
            disposable = EmployeeRepository().getRealTimeEmployeeInfo()
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread()).subscribe(onNext)
        }
    }

    fun stop() {
        if (disposable != null && !disposable!!.isDisposed) {
            println("stopped")
            disposable!!.dispose()
        }
    }
}