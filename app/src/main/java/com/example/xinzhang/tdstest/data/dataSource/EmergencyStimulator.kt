package com.example.xinzhang.tdstest.data.dataSource


import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

import java.util.Random
import java.util.concurrent.TimeUnit

private const val EMERGENCY_INTERVAL: Long = 60
private const val EMERGENCY_DELAY: Long = 30

private var inited: Boolean = false

class EmergencyStimulator {
    fun init(): Flowable<Boolean> {
        return Observable.interval(0, EMERGENCY_INTERVAL, TimeUnit.SECONDS)
            .concatMap {
                Observable.timer(if (inited) Random().nextInt(EMERGENCY_INTERVAL.toInt()) + EMERGENCY_DELAY else  1, TimeUnit.SECONDS)
                    .doOnNext{inited = true}
                    .map {Random().nextBoolean() }
            }
            .toFlowable(BackpressureStrategy.DROP)
    }
}
