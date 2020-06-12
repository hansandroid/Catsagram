package com.hansandroid.catsagram.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hansandroid.catsagram.R

abstract class SingleFragmentActivity : AppCompatActivity() {

    abstract fun createFragment() : Fragment
    abstract fun replaceFragment(fragment: Fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

}