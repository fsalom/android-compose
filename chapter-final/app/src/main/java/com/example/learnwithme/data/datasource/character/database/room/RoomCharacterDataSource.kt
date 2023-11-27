package com.example.learnwithme.data.datasource.character.database.room

import com.example.learnwithme.data.datasource.character.database.CharacterDatabaseDataSourceInterface
import com.example.learnwithme.data.datasource.character.database.room.dbo.CharacterEntity
import com.example.learnwithme.data.datasource.character.database.room.query.CharacterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomCharacterDataSource(
    val dao: CharacterDao
): CharacterDatabaseDataSourceInterface {

    private val dispatcher = Dispatchers.IO
    override suspend fun getFavorites(): List<CharacterEntity> {
        return withContext(dispatcher) {
            dao.getFavorites()
        }
    }

    override suspend fun getNextPageAndCharacters(page: Int): Pair<Boolean, List<CharacterEntity>> {
        return withContext(dispatcher) {
            val charactersEntity = dao.getCharacters(page)
            Pair(true, charactersEntity)
        }
    }

    override suspend fun search(text: String): List<CharacterEntity> {
        return withContext(dispatcher) {
            dao.searchCharacters(text)
        }
    }

    override suspend fun getCharacterWith(id: Int): CharacterEntity? {
        return withContext(dispatcher) {
            dao.getEntitySync(id)
        }
    }

    override suspend fun save(characters: List<CharacterEntity>, page: Int) {
        return withContext(dispatcher) {
            for (character in characters) {
                dao.insertOrReplace(character)
            }
        }
    }

    override suspend fun favOrUnFav(character: CharacterEntity) {
        return withContext(dispatcher) {
            dao.insertOrReplace(character)
        }
    }
}