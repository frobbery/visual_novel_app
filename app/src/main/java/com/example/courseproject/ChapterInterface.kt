package com.example.courseproject

import androidx.fragment.app.Fragment

interface ChapterInterface {

    val fragments : Array<Fragment?>
    val choices : Array<String>

    fun initializeFragments()
    fun reinitializeFragments()
    fun continueToFrame(frameNumber: Int)
}