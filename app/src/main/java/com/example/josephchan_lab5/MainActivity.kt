package com.example.josephchan_lab5

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        createAndAddFragments()
//        val mediaPlayer = MediaPlayer.create(this, R.raw.Star)
//        mediaPlayer.start()
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