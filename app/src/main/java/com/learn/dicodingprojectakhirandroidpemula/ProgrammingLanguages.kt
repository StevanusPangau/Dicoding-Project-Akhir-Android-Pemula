package com.learn.dicodingprojectakhirandroidpemula

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProgrammingLanguages(
    var name: String,
    var description: String,
    var photo: Int,
    var creator: String,
    var year: String,
    var website: String,
    var excess: String
) : Parcelable
