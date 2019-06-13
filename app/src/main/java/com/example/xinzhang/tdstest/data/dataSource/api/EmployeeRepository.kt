package com.example.xinzhang.tdstest.data.dataSource.api


import com.example.xinzhang.tdstest.data.dataModel.Employee
import com.example.xinzhang.tdstest.data.dataSource.api.APIService
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit


private const val EMPLOYEE_CALL_INTERVAL: Long = 5

class EmployeeRepository {

    fun getRealTimeEmployeeInfo(): Flowable<List<Employee>> {
        return Flowable.interval(EMPLOYEE_CALL_INTERVAL,TimeUnit.SECONDS)
            .flatMap { requestEmployees().toFlowable(BackpressureStrategy.DROP) }
    }
    private fun requestEmployees(): io.reactivex.Observable<List<Employee>> {
        return APIService.create().employees()
    }

}