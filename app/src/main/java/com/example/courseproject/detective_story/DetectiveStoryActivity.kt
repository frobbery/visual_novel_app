package com.example.courseproject.detective_story

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.courseproject.R

class DetectiveStoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detective_story)

        val sp : SharedPreferences = getSharedPreferences("Detective Story", Context.MODE_PRIVATE)
        val chapterNumber = sp.getInt("chapter number", 1)
        startDetectiveStoryChapter(chapterNumber)
    }

    private fun startDetectiveStoryChapter(chapterNumber: Int) {
        if (chapterNumber == 1) {
            val intent = Intent(this, DetectiveStoryChapterFirst::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.log).text = chapterNumber.toString()
    }
}