package com.example.learnwithme.data.datasource

import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character

interface CharacterDataSourceInterface {
    suspend fun getPagination(page: Int): Pagination
    suspend fun getCharacterWith(id: Int): Character?
}
