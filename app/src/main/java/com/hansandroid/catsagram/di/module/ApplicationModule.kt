package com.hansandroid.catsagram.di.module

import android.app.Application
import com.hansandroid.catsagram.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Application = mApplication

}