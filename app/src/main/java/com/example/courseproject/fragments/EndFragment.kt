package com.example.courseproject.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.courseproject.MainActivity
import com.example.courseproject.R

class EndFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_end, container, false)
        view.setOnClickListener {
            val sp : SharedPreferences = context?.getSharedPreferences("detective story",
                AppCompatActivity.MODE_PRIVATE)!!
            val editor : SharedPreferences.Editor = sp.edit()
            editor.putInt("chapter number", sp.getInt("chapter number", 1) + 1).apply()
            editor.putInt("frame number", 0).apply()
            editor.putBoolean("started", false).apply()
            val intent = Intent(context, MainActivity::class.java)
            view.context.startActivity(intent)
        }
        return view
    }
}