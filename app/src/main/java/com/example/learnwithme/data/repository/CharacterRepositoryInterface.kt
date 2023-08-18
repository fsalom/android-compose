package com.example.learnwithme.data.repository

import com.example.learnwithme.domain.entity.Pagination

interface CharacterRepositoryInterface {
    suspend fun getPagination(page: Int): Pagination
}