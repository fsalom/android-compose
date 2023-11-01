package com.example.learnwithme.data.datasource.character.database.room

import com.example.learnwithme.data.datasource.character.database.CharacterDatabaseDataSourceInterface
import com.example.learnwithme.data.datasource.character.database.room.dbo.CharacterEntity
import com.example.learnwithme.data.datasource.character.database.room.dbo.toDomain
import com.example.learnwithme.data.datasource.character.database.room.query.CharacterDao
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.domain.entity.Pagination

class RoomCharacterDataSource(
    val dao: CharacterDao
): CharacterDatabaseDataSourceInterface {
    override suspend fun getFavorites(): List<Character> {
        val charactersEntity = dao.getFavorites()
        return charactersEntity.map { it.toDomain() }
    }

    override suspend fun getPagination(page: Int): Pagination {
        val charactersEntity = dao.getCharacters(page)
        return Pagination(characters = charactersEntity.map { it.toDomain() }, hasNextPage = true)
    }

    override suspend fun search(text: String): List<Character> {
        val charactersEntity = dao.searchCharacters(text)
        return charactersEntity.map { it.toDomain() }
    }

    override suspend fun getCharacterWith(id: Int): Character? {
        val character = dao.getEntitySync(id)
        if (character != null) {
            return character.toDomain()
        }
        return null
    }

    override suspend fun save(characters: List<Character>, page: Int) {
        for (character in characters) {
            dao.insertOrReplace(CharacterEntity.create(character = character, page = page))
        }
    }

    override suspend fun favOrUnFav(character: Character) {
        val entity = dao.getEntitySync(character.id)
        character.isFavorite = !character.isFavorite
        val newCharacterEntity = CharacterEntity.create(character, entity?.page ?: 0)
        dao.insertOrReplace(newCharacterEntity)
    }
}