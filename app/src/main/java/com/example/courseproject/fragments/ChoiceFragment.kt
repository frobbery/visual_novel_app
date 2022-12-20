package com.example.courseproject.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.courseproject.ChapterInterface
import com.example.courseproject.MainActivity
import com.example.courseproject.R

class ChoiceFragment(@DrawableRes private val background: Int,
                     private val firstChoice: String,
                     private val secondChoice: String,
                     private val sp : SharedPreferences,
                     private val fragments: Array<Fragment?>,
                     private val activity: ChapterInterface
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_choice, container, false)
        val goBack : ImageView = view.findViewById(R.id.back_button)

        val firstChoiceText : TextView = view.findViewById(R.id.first_choice_text)
        firstChoiceText.text = firstChoice

        val secondChoiceText : TextView = view.findViewById(R.id.second_choice_text)
        secondChoiceText.text = secondChoice

        val imageView : ImageView = view.findViewById(R.id.background)
        imageView.setImageResource(background)

        val firstChoiceButton : ImageView = view.findViewById(R.id.first_choice)
        val secondChoiceButton : ImageView = view.findViewById(R.id.second_choice)

        val editor : Editor = sp.edit()

        val frameNumber = sp.getInt("frame number", 0)

        firstChoiceButton.setOnClickListener {
            editor.putBoolean(firstChoice, true).apply()
            editor.putBoolean(secondChoice, false).apply()
            goToNextFragment(frameNumber, editor)
        }

        secondChoiceButton.setOnClickListener {
            editor.putBoolean(firstChoice, false).apply()
            editor.putBoolean(secondChoice, true).apply()
            goToNextFragment(frameNumber, editor)
        }

        goBack.setOnClickListener {
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
        }
        return view
    }

    private fun goToNextFragment(frameNumber : Int, e: Editor) {
        e.putInt("frame number", frameNumber + 1).apply()
        activity.reinitializeFragments()
        fragments[frameNumber + 1]?.let { it1 ->
            parentFragmentManager.beginTransaction().replace(R.id.detective_chapter_first_frame,
                it1
            ).commit()
        }
    }
}