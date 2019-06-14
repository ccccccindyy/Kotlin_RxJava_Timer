package com.example.xinzhang.tdstest.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.xinzhang.tdstest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(
            R.id.fragment_container,
            EmergencyControlFragment()
        )
        ft.commit()
    }
}
