package com.example.felinefinder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Data(
    var name: String,
    var breed: String,
    var long: Double,
    var lat: Double,
    var age: Int,
    var lost: Boolean,
    var description: String,
    var friendly: String,
    var lastSeen: Date,
    val image: String
): Parcelable