package com.example.courseproject.data

import com.example.courseproject.R

object Stories {
    fun getStoriesList() : List<Story> {
        return listOf(
            Story("detective story", 1, R.drawable.detective_story_cover),
            Story("Not done story", 0, R.drawable.undone_story_cover)
        )
    }
}
