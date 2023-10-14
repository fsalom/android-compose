package com.example.learnwithme.data.repository

import com.example.learnwithme.data.datasource.character.CharacterDataSourceInterface
import com.example.learnwithme.data.datasource.favorite.FavoriteCharacterDataSourceInterface
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character

class CharacterRepository(val characterDataSource: CharacterDataSourceInterface,
                          val favoriteDatasource: FavoriteCharacterDataSourceInterface
): CharacterRepositoryInterface {
    override suspend fun getPagination(page: Int): Pagination {
        return characterDataSource.getPagination(page)
    }

    override suspend fun getPaginationFor(text: String, page: Int): Pagination {
        return characterDataSource.getPaginationFor(text, page)
    }

    override suspend fun getCharacterWith(id: Int): Character? {
        return characterDataSource.getCharacterWith(id)
    }

    override suspend fun saveFavorite(character: Character) {
        favoriteDatasource.saveThis(character)
    }

    override suspend fun deleteFavorite(character: Character) {
        favoriteDatasource.deleteThis(character)
    }

    override suspend fun getFavoriteCharacters(): List<Character> {
        return favoriteDatasource.getCharacters()
    }


}