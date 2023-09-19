package com.example.learnwithme.manager.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
/*
class DatabaseManager(
    private val dao: CharacterDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun observeCharacters(): Flow<List<CharacterEntity>> =
        dao.observeCharacters()


    suspend fun getCharacters(): List<CharacterEntity> = withContext(dispatcher) {
        dao.getCharacters()
    }

    suspend fun getCharacter(id: Int): CharacterEntity = withContext(dispatcher) {
        dao.getCharacter(id)

    }

    suspend fun saveCharacters(character: List<CharacterEntity>) = withContext(dispatcher) {
        dao.insertCharacters(character.map {
            it
        })
    }

    suspend fun saveCharacter(character: CharacterEntity) = withContext(dispatcher) {
        dao.insertCharacter(character)
    }

    suspend fun removeAllCharacters() = withContext(dispatcher) {
        dao.deleteCharacters()
    }
}
*/