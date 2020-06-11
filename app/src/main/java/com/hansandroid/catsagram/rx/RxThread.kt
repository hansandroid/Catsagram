package com.hansandroid.catsagram.rx

import com.hansandroid.catsagram.di.module.RxThreadModule
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

class RxThread @Inject constructor(@Named(RxThreadModule.mainThread) private val mainScheduler: Scheduler,
                                   @Named(RxThreadModule.ioThread) private val ioScheduler: Scheduler) {

    fun <T> applyAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        }
    }


}