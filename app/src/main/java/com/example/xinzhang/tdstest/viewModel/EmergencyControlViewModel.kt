package com.example.xinzhang.tdstest.viewModel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.example.xinzhang.tdstest.data.dataModel.Employee
import com.example.xinzhang.tdstest.data.dataSource.EmergencyControlCallHandler
import com.example.xinzhang.tdstest.data.dataSource.EmergencyStimulator
import com.example.xinzhang.tdstest.data.dataSource.api.EMPLOYEE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class EmergencyControlViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val employeeControlSubject: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    val hasEmergency = ObservableBoolean(false)
    val totalNumber: ObservableField<String> = ObservableField("")
    val ageUnder18Number: ObservableField<String> = ObservableField("")
    val ageUnder18to60Number: ObservableField<String>  = ObservableField("")
    val ageOver60: ObservableField<String>  = ObservableField("")
    val isLoading: ObservableBoolean = ObservableBoolean(false) // refresh every 5 seconds


    fun initEmergencyCondition() {
        val controlHandler = EmergencyControlCallHandler()
        compositeDisposable.addAll(
            EmergencyStimulator().init()
                .doOnNext { emergency -> employeeControlSubject.onNext(emergency) }
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { b -> Log.d("Emergency Event", b.toString()) },

            employeeControlSubject
            .doOnNext { emergency -> run {
                    if (emergency) controlHandler.resume(Consumer { employees -> updateEmergencyDisplay(employees) }) else controlHandler.stop()
                }
            }
            .subscribe{ a -> run {
                hasEmergency.set(a)
            }},

            controlHandler.loading.subscribe{this.isLoading.set(true)}
            )
    }

    fun clearDispose() {
        compositeDisposable.clear()
    }

    private fun updateEmergencyDisplay(employees: List<Employee>){
        isLoading.set(false)
        totalNumber.set(employees.size.toString())
        Log.d("Total number", employees.size.toString())
        ageUnder18Number.set(employees.filter { employee -> employee.age < 18 }.size.toString())
        Log.d("Age under 18", employees.filter { employee -> employee.age < 18 }.size.toString())
        ageUnder18to60Number.set(employees.filter { employee -> employee.age in 18..60 }.size.toString())
        Log.d("Age under 60", employees.filter { employee -> employee.age in 18..60 }.size.toString())
        ageOver60.set( employees.filter { employee -> employee.age > 60 }.size.toString())
        Log.d("Age over 60", employees.filter { employee -> employee.age > 60 }.size.toString())
    }
}