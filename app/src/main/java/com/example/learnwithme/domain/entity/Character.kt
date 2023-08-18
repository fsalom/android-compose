package com.example.learnwithme.domain.entity

import com.example.learnwithme.data.datasource.character.remote.dto.CharacterDTO
import java.io.Serializable

data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val image: String = "",
    val episodes: List<String> = listOf()
): Serializable