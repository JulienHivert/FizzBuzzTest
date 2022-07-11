package com.kreactive.fizzbuzz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kreactive.fizzbuzz.fragments.SettingsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, SettingsFragment.newInstance(), SettingsFragment.TAG)
            commit()
        }
    }
}