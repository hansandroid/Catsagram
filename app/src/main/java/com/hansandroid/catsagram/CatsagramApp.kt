package com.hansandroid.catsagram

import android.app.Application
import com.hansandroid.catsagram.di.component.AppComponent
import com.hansandroid.catsagram.di.component.DaggerAppComponent
import com.hansandroid.catsagram.di.module.HttpModule

class CatsagramApp : Application() {

    lateinit var mComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    private fun createComponent() {
        mComponent = DaggerAppComponent.builder()
            .httpModule(HttpModule())
            .build()
    }

}