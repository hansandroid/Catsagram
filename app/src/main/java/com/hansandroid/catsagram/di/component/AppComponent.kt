package com.hansandroid.catsagram.di.component

import com.hansandroid.catsagram.di.module.ActivityModule
import com.hansandroid.catsagram.di.module.HttpModule
import com.hansandroid.catsagram.di.module.RxThreadModule
import com.hansandroid.catsagram.view.fragment.BreedsFragment
import com.hansandroid.catsagram.view.fragment.CatImagesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, ActivityModule::class, RxThreadModule::class])
interface AppComponent {

    fun inject(fragment: BreedsFragment)
    fun inject(fragment: CatImagesFragment)

}