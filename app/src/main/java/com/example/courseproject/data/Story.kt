package com.example.courseproject.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class Story(val storyName: String,
                 val chaptersAvailable: Int,
                 @DrawableRes val background: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(storyName)
        parcel.writeInt(chaptersAvailable)
        parcel.writeInt(background)
     }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Story> {
        override fun createFromParcel(parcel: Parcel): Story {
            return Story(parcel)
        }

        override fun newArray(size: Int): Array<Story?> {
            return arrayOfNulls(size)
        }
    }
}