package com.example.learnwithme.domain.entity

import java.io.Serializable

data class Pagination(
    val hasNextPage: Boolean,
    val characters: List<Character> = listOf()
): Serializable