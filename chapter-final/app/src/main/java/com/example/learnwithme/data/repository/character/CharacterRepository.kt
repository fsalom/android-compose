package com.example.learnwithme.data.repository.character

import com.example.learnwithme.data.datasource.character.database.CharacterDatabaseDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.CharacterRemoteDataSourceInterface
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character

class CharacterRepository(
    private val remoteDataSource: CharacterRemoteDataSourceInterface,
    private val databaseDatasource: CharacterDatabaseDataSourceInterface
): CharacterRepositoryInterface {

    private val MILISECONDS_TO_UPDATE: Long = 3000
    override suspend fun getPagination(page: Int): Pagination {
        var localCharacters = databaseDatasource.getPagination(page)
        return if (localCharacters.characters.isEmpty()) {
            var pagination = remoteDataSource.getPagination(page)
            databaseDatasource.save(pagination.characters, page = page)
            pagination
        } else {
            if (shouldBeUpdated(localCharacters.characters.first().creationDate)) {
                val favoriteIds = localCharacters.characters.filter { it.isFavorite }.map { it.id }
                var pagination = remoteDataSource.getPagination(page)
                pagination.characters.forEach {
                    if (it.id in favoriteIds) {
                        it.isFavorite = true
                    }
                }
                databaseDatasource.save(pagination.characters, page = page)
                pagination
            } else {
                localCharacters
            }
        }
    }

    override suspend fun getPaginationFor(text: String, page: Int): Pagination {
        return remoteDataSource.getPaginationFor(text, page)
    }

    override suspend fun getCharacterWith(id: Int): Character? {
        var localCharacter = databaseDatasource.getCharacterWith(id)
        return if (localCharacter == null) {
            remoteDataSource.getCharacterWith(id)
        } else {
            localCharacter
        }
    }

    override suspend fun favOrUnFav(character: Character) {
        databaseDatasource.favOrUnFav(character)
    }

    override suspend fun getFavoriteCharacters(): List<Character> {
        return databaseDatasource.getFavorites()
    }

    private fun shouldBeUpdated(creationTime: Long): Boolean {
        var currentTime = System.currentTimeMillis()
        if (currentTime - creationTime > MILISECONDS_TO_UPDATE) {
            return true
        }
        return false
    }
}