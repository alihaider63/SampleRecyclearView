package com.example.samplerecyclearview.models

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.IntegerRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Butterfly(
    var name: String? = "Ali",
    var family: String? = "Haider",
    @IntegerRes var butterfly: Int? = null,
    var imagePath: String? = null,
    var image: Bitmap? = null,
    var count: Int = 0
) : Parcelable
