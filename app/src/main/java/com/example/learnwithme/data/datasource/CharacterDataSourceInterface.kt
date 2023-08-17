package com.example.learnwithme.data.datasource

import com.example.learnwithme.domain.entity.Pagination

interface CharacterDataSourceInterface {
    suspend fun getPagination(page: Int): Pagination
}
