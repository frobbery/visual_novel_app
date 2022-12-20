package com.example.courseproject.detective_story

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.courseproject.ChapterInterface
import com.example.courseproject.R
import com.example.courseproject.fragments.BackgroundFragment
import com.example.courseproject.fragments.ChoiceFragment
import com.example.courseproject.fragments.DialogFragment
import com.example.courseproject.fragments.EndFragment

class DetectiveStoryChapterFirst : AppCompatActivity(), ChapterInterface {

    override val fragments : Array<Fragment?> = arrayOfNulls(6)
    override val choices : Array<String> = arrayOf("First choice", "Second choice")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detective_story_chapter1)
        val sp : SharedPreferences = getSharedPreferences("detective story", Context.MODE_PRIVATE)
        val frameNumber = sp.getInt("frame number", 0)
        sp.edit().putBoolean("started", true).apply()

        initializeFragments()

        continueToFrame(frameNumber)
    }

    override fun initializeFragments() {
        val sp : SharedPreferences = getSharedPreferences("detective story", Context.MODE_PRIVATE)

        fragments[0] = BackgroundFragment(R.drawable.subway, "This is background fragment", fragments)
        fragments[1] = DialogFragment(
            R.drawable.subway,
            R.drawable.character,
            true,
            "Main",
            "This is dialog from main",
            fragments)
        fragments[2] = DialogFragment(
            R.drawable.subway,
            R.drawable.character,
            false,
            "notMain",
            "This is dialog from notMain",
            fragments)
        fragments[3] = ChoiceFragment(
            R.drawable.subway,
            choices[0],
            choices[1],
            sp,
            fragments,
        this)
        fragments[5] = EndFragment()
    }

    override fun reinitializeFragments() {
        val sp : SharedPreferences = getSharedPreferences("detective story", Context.MODE_PRIVATE)

        val chosenFirst = sp.getBoolean(choices[0], true)
        if (chosenFirst) {
            fragments[4] = BackgroundFragment(R.drawable.subway, "You choose first", fragments)
        }
        else {
            fragments[4] = BackgroundFragment(R.drawable.subway, "You choose second", fragments)
        }
    }


    override fun continueToFrame(frameNumber: Int) {
        fragments[frameNumber]?.let {
            supportFragmentManager.beginTransaction().replace(
                R.id.detective_chapter_first_frame,
                it
            ).commit()
        }
    }
}