package com.hansandroid.catsagram.di.module

import android.app.Activity
import android.content.Context
import com.hansandroid.catsagram.di.ActivityContext
import com.hansandroid.catsagram.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: Activity) {

    @ActivityScope
    @Provides
    @ActivityContext
    fun provideContext() : Context = mActivity

}