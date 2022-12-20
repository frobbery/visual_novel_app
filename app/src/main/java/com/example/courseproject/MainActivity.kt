package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.courseproject.fragments.StoryListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openStoryListFragment()
        setContentView(R.layout.activity_main)
    }

    private fun openStoryListFragment() {
        val storyListFragment = StoryListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_frame, storyListFragment).commit()
    }


}