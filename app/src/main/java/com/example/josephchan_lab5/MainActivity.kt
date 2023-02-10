package com.example.josephchan_lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        createAndAddFragments()
    }


    private fun createAndAddFragments(){
        val ft = supportFragmentManager.beginTransaction()

        val arenaFragment = ArenaFragment.newInstance()
        val homeFragment = HomeFragment.newInstance()

        ft.add(R.id.fragmentContainer_main, arenaFragment, "ARENAFRAGMENT_TAG")
        ft.add(R.id.fragmentContainer_main, homeFragment, "HOMEFRAGMENT_TAG")
        ft.hide(arenaFragment)
        ft.commit()
    }

}