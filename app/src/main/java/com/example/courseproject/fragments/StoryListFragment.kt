package com.example.courseproject.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.R
import com.example.courseproject.data.Stories
import com.example.courseproject.data.StoryAdapter

class StoryListFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    @SuppressLint("MissingInflatedId", "UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_story_list, container, false)
        val stories = Stories.getStoriesList()
        val adapter = this.context?.let { StoryAdapter(it, stories)}

        val list = view.findViewById<RecyclerView>(R.id.storyList)
        list.adapter = adapter

        return view
    }

    private fun setWindowParams() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}