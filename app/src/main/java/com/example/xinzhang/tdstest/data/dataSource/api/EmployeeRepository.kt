package com.example.xinzhang.tdstest.data.dataSource.api


import com.example.xinzhang.tdstest.data.dataModel.Employee
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


private const val EMPLOYEE_CALL_INTERVAL: Long = 5

class EmployeeRepository {
    val loading = PublishSubject.create<Boolean>()

    fun getRealTimeEmployeeInfo(): Flowable<List<Employee>> {
        return Flowable.interval(0, EMPLOYEE_CALL_INTERVAL,TimeUnit.SECONDS)
            .doOnNext{loading.onNext(true)}
            .flatMap { requestEmployees().toFlowable(BackpressureStrategy.DROP) }
    }
    private fun requestEmployees(): io.reactivex.Observable<List<Employee>> {
        return APIService.create().employees()
    }

}