package com.example.felinefinder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat (
    var name : String,
    var breed : String,
    var age : Int,
    var lost : Boolean,
    var long : Double,
    var lat : Double,
    var description: String,
    var friendly : String,
    val image: String
): Parcelable