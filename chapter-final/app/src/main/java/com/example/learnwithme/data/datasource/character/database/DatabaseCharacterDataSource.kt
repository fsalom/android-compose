package com.example.learnwithme.data.datasource.character.database

import com.example.learnwithme.data.datasource.character.CharacterDataSourceInterface
import com.example.learnwithme.data.datasource.character.database.dbo.toDomain
import com.example.learnwithme.data.datasource.character.database.query.CharacterDao
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.domain.entity.Pagination

class DatabaseCharacterDataSource(
    val dao: CharacterDao
): CharacterDataSourceInterface {
    override suspend fun getPagination(page: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getPaginationFor(text: String, page: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterWith(id: Int): Character? {
        var character = dao.getEntitySync(id)
        if (character != null) {
            return character.toDomain()
        }
        return null
    }

}