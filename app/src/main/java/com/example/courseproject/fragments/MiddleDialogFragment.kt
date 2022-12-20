package com.example.courseproject.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.courseproject.R

class MiddleDialogFragment(private val param1: String) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_middle_dialog, container, false)
        val text : TextView = view.findViewById(R.id.text)
        val borders: ImageView = view.findViewById(R.id.borders)
        text.text = param1
        borders.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
        return view
    }

    private fun setWindowParams() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}