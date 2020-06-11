package com.hansandroid.catsagram.di.module

import androidx.fragment.app.Fragment
import com.hansandroid.catsagram.view.BreedsFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentsModule {

    @Provides
    fun provideCatsListFragment() : Fragment {
        return BreedsFragment()
    }

}