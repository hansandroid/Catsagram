package com.hansandroid.catsagram.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class RxThreadModule {

    @Singleton
    @Provides
    @Named(mainThread)
    fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    @Named(ioThread)
    fun provideSchedulerIO(): Scheduler = Schedulers.io()

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }

}