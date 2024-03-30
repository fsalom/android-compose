package com.example.learnwithme.domain

import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character

interface CharacterRepositoryInterface {
    suspend fun getPagination(page: Int): Pagination
    suspend fun getPaginationFor(text: String, page: Int): Pagination
    suspend fun getCharacterWith(id: Int): Character?
}