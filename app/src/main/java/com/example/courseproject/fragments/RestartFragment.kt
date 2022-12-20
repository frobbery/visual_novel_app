package com.example.courseproject.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.courseproject.MainActivity
import com.example.courseproject.R

class RestartFragment(private var storyName: String) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.restart_fragment, container, false)
        val restartChapter : ImageView = view.findViewById(R.id.restart_chapter)
        val restartStory : ImageView = view.findViewById(R.id.restart_story)
        val closeFragment : ImageView = view.findViewById(R.id.close_fragment)

        closeFragment.setOnClickListener {
            closeFragment(view)
        }

        restartChapter.setOnClickListener {
            restartChapter(storyName, view)
            closeFragment(view)
        }

        restartStory.setOnClickListener {
            val sp : SharedPreferences = view.context.getSharedPreferences(storyName, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sp.edit()
            val chapterNumberString = "chapter number"
            val frameNumberString = "frame number"
            editor.putInt(frameNumberString, 0).apply()
            editor.putInt(chapterNumberString, 1).apply()
            closeFragment(view)
        }
        return view
    }

    private fun restartChapter(storyName: String, view : View) {
        val sp : SharedPreferences = view.context.getSharedPreferences(storyName, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sp.edit()
        val frameNumberString = "frame number"
        editor.putInt(frameNumberString, 0).apply()
        editor.putBoolean("started", false).apply()
    }

    private fun closeFragment(view: View) {
        val intent = Intent(context, MainActivity::class.java)
        view.context.startActivity(intent)
        parentFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun setWindowParams() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}