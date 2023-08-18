package com.example.learnwithme.data.repository

import com.example.learnwithme.data.datasource.CharacterDataSourceInterface
import com.example.learnwithme.domain.entity.Pagination

class CharacterRepository(val dataSource: CharacterDataSourceInterface): CharacterRepositoryInterface {
    override suspend fun getPagination(page: Int): Pagination {
        return dataSource.getPagination(page)
    }
}