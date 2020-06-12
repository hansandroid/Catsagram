package com.hansandroid.catsagram.di.module

import androidx.fragment.app.Fragment
import com.hansandroid.catsagram.view.fragment.BreedsFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentsModule {

    @Singleton
    @Provides
    fun provideCatsListFragment() : Fragment {
        return BreedsFragment()
    }

}