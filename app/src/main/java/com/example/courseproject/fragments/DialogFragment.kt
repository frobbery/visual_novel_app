package com.example.courseproject.fragments

import android.content.Context
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
import com.example.courseproject.MainActivity
import com.example.courseproject.R

class DialogFragment(@DrawableRes private val background: Int,
                     @DrawableRes private val character: Int,
                     private val main: Boolean,
                     private val name: String,
                     private val text:String,
                     private val fragments: Array<Fragment?>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        val borders : ImageView = view.findViewById(R.id.borders)
        val mainBorders : ImageView = view.findViewById(R.id.borders_main)
        val goBack : ImageView = view.findViewById(R.id.back_button)
        val textView : TextView = view.findViewById(R.id.text)
        textView.text = text
        val imageView : ImageView = view.findViewById(R.id.background)
        imageView.setImageResource(background)

        val mainName : TextView = view.findViewById(R.id.main_name)
        val mainChar : ImageView = view.findViewById(R.id.main_character)

        if (main) {
            mainName.text = name
            mainChar.setImageResource(character)
        }
        else {
            val mainNameContainer : ImageView = view.findViewById(R.id.main_name_container)
            mainNameContainer.visibility = View.INVISIBLE
            mainName.visibility = View.INVISIBLE
            mainChar.visibility = View.INVISIBLE
            mainBorders.visibility = View.INVISIBLE

            val charName : TextView = view.findViewById(R.id.name)
            charName.visibility = View.VISIBLE
            charName.text = name
            val nameContainer : ImageView = view.findViewById(R.id.name_container)
            nameContainer.visibility = View.VISIBLE
            borders.visibility = View.VISIBLE
            val char : ImageView = view.findViewById(R.id.character)
            char.visibility = View.VISIBLE
            char.setImageResource(character)
        }

        val sp: SharedPreferences =
            context?.getSharedPreferences("detective story", Context.MODE_PRIVATE)!!
        val frameNumber = sp.getInt("frame number", 1)
        val editor : Editor = sp.edit()

        goBack.setOnClickListener {
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
        }

        view.setOnClickListener {
            goToNextFragment(frameNumber, editor)
        }

        return view
    }

    private fun goToNextFragment(frameNumber : Int, editor : Editor) {
        editor.putInt("frame number", frameNumber + 1).apply()
        fragments[frameNumber + 1]?.let { it1 ->
            parentFragmentManager.beginTransaction().replace(R.id.detective_chapter_first_frame,
                it1
            ).commit()
        }
    }
}