package com.example.learnwithme.data.repository.character

import com.example.learnwithme.data.datasource.character.database.CharacterDatabaseDataSourceInterface
import com.example.learnwithme.data.datasource.character.database.room.dbo.CharacterEntity
import com.example.learnwithme.data.datasource.character.remote.CharacterRemoteDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharacterDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.domain.repository.CharacterRepositoryInterface

class CharacterRepository(
    private val remoteDataSource: CharacterRemoteDataSourceInterface,
    private val databaseDatasource: CharacterDatabaseDataSourceInterface
): CharacterRepositoryInterface {

    private val millisecondsToUpdate: Long = 3000
    override suspend fun getPagination(page: Int): Pagination {
        val localPagination = databaseDatasource.getNextPageAndCharacters(page)
        val localCharactersIsEmpty = localPagination.second.isEmpty()
        val firstCharacter = if(localCharactersIsEmpty) null else localPagination.second.first()
        val shouldBeUpdated = if(firstCharacter == null) false else shouldBeUpdated(firstCharacter.creationDate)
        return if (localCharactersIsEmpty || shouldBeUpdated) {
            val remotePagination = remoteDataSource.getPagination(page)
            var characters = remotePagination.results.map { it.toDomain() }
            characters = setFavorites(characters)
            databaseDatasource.save(characters.map { it.ToDBO() }, page = page)
            remotePagination.toDomain()
        } else {
            Pagination(
                hasNextPage = localPagination.first,
                characters = localPagination.second.map { it.toDomain() })
        }
    }

    override suspend fun getPaginationFor(text: String, page: Int): Pagination {
        return remoteDataSource.getPaginationFor(text, page).toDomain()
    }

    override suspend fun getCharacterWith(id: Int): Character? {
        return databaseDatasource.getCharacterWith(id)?.toDomain() ?: remoteDataSource.getCharacterWith(id)
            ?.toDomain()
    }

    override suspend fun favOrUnFav(character: Character) {
        databaseDatasource.favOrUnFav(character.ToDBO())
    }

    override suspend fun getFavoriteCharacters(): List<Character> {
        return databaseDatasource.getFavorites().map { it.toDomain() }
    }

    private fun shouldBeUpdated(creationTime: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - creationTime > millisecondsToUpdate) {
            return true
        }
        return false
    }

    private suspend fun setFavorites(characters: List<Character>): List<Character> {
        val favoriteIds = getFavoriteCharacters().map { it.id }
        val favoriteCharacters: List<Character> = characters
        favoriteCharacters.forEach {
            if (it.id in favoriteIds) {
                it.isFavorite = true
            }
        }
        return favoriteCharacters
    }
}

private fun CharactersInfoDTO.toDomain(): Pagination {
    return Pagination(
        hasNextPage = info?.next != null,
        characters = results.map { it.toDomain() }
    )
}

private fun Character.ToDBO(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        image = image,
        isFavorite = isFavorite,
        page = page
    )
}

private fun CharacterEntity.toDomain(): Character =
    Character(
        id = id,
        name = name ?: "",
        status = status ?: "",
        species = species ?: "",
        image = image ?: "",
        isFavorite = isFavorite,
        page = page,
        creationDate = creationDate
    )

private fun CharacterDTO.toDomain(): Character =
    Character(
        id = id ?: 0,
        name = name ?: "",
        status = status ?: "",
        species = species ?: "",
        image = image ?: ""
    )