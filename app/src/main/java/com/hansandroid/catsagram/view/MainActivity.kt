package com.hansandroid.catsagram.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.presenter.MainActivityPresenter

class MainActivity : SingleFragmentActivity(), MainActivityPresenter.View {

    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createFragment(): Fragment {
        Log.d(TAG, "createFragment")
        return BreedsFragment()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
    }

}
