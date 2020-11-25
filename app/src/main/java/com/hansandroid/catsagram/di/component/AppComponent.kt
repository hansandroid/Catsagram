package com.hansandroid.catsagram.di.component

import com.hansandroid.catsagram.di.module.ActivityModule
import com.hansandroid.catsagram.di.module.HttpModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, ActivityModule::class])
interface AppComponent {



}