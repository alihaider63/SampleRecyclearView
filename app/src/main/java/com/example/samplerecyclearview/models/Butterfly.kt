package com.example.samplerecyclearview.models

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.IntegerRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Butterfly(
    val butterflyName: String? = "Brush-footed",
    val family: String? = "Nymphalidae",
    @IntegerRes val imageRes: Int? = null,
    val imagePath: String? = null,
    val imageBitmap: Bitmap? = null,
    var count: Int = 0
) : Parcelable
