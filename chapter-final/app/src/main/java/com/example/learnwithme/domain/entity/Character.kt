package com.example.learnwithme.domain.entity

import android.os.Parcelable
import java.io.Serializable

data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val image: String = "",
    var isFavorite: Boolean = false
): Serializable