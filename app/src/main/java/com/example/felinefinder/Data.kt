package com.example.felinefinder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Data(
    var name: String,
    var friendly: String,
    var description: String,
    var lat: Double,
    var long: Double,
    var lost: Boolean
): Parcelable