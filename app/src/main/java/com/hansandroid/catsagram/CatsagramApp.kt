package com.hansandroid.catsagram

import android.app.Application
import com.hansandroid.catsagram.di.component.AppComponent
import com.hansandroid.catsagram.di.component.DaggerAppComponent
import com.hansandroid.catsagram.di.module.ApplicationModule
import com.hansandroid.catsagram.di.module.HttpModule

class CatsagramApp : Application() {

    lateinit var mComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    fun createComponent() {
        mComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .httpModule(HttpModule())
            .build()
    }

}