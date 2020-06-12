package com.hansandroid.catsagram.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.presenter.MainActivityPresenter
import com.hansandroid.catsagram.view.fragment.BreedsFragment

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
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
