package com.hansandroid.catsagram.di.component

import com.hansandroid.catsagram.di.module.ActivityModule
import com.hansandroid.catsagram.di.module.ApplicationModule
import com.hansandroid.catsagram.di.module.HttpModule
import com.hansandroid.catsagram.di.module.RxThreadModule
import com.hansandroid.catsagram.view.BreedsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, ActivityModule::class, ApplicationModule::class, RxThreadModule::class])
interface AppComponent {

    fun inject(fragment: BreedsFragment)

}