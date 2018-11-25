package com.f8full.somearchitecture

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.f8full.somearchitecture.ui.connectivityinfo.ConnectivityInfoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ConnectivityInfoFragment.newInstance())
                .commitNow()
        }
    }

}
