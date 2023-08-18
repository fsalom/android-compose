package com.example.learnwithme.domain.entity

import com.example.learnwithme.data.datasource.character.remote.dto.CharacterDTO
import com.example.learnwithme.data.datasource.character.remote.dto.CharactersInfoDTO
import com.example.learnwithme.data.datasource.character.remote.dto.toDomain
import java.io.Serializable

data class Pagination(
    val hasNextPage: Boolean,
    val characters: List<Character> = listOf()
): Serializable