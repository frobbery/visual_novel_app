package com.example.courseproject.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.courseproject.MainActivity
import com.example.courseproject.R

class BackgroundFragment(@DrawableRes private val background: Int,
                         private val text: String,
                         private val fragments: Array<Fragment?>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_background, container, false)
        val goBack : ImageView = view.findViewById(R.id.back_button)
        val textView : TextView = view.findViewById(R.id.text)
        textView.text = text
        val imageView : ImageView = view.findViewById(R.id.background)
        imageView.setImageResource(background)

        val sp: SharedPreferences =
            context?.getSharedPreferences("detective story", Context.MODE_PRIVATE)!!
        val frameNumber = sp.getInt("frame number", 0)
        val editor : SharedPreferences.Editor = sp.edit()

        goBack.setOnClickListener {
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
        }

        view.setOnClickListener {
            editor.putInt("frame number", frameNumber + 1).apply()
            fragments[frameNumber + 1]?.let { it1 ->
                parentFragmentManager.beginTransaction().replace(R.id.detective_chapter_first_frame,
                    it1
                ).commit()
            }
        }
        return view
    }
}