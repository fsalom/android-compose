package com.example.learnwithme.data.datasource.character.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)