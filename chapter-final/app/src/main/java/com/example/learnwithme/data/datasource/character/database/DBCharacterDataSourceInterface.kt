package com.example.learnwithme.data.datasource.character.database

import com.example.learnwithme.data.datasource.character.database.room.dbo.CharacterEntity
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.domain.entity.Pagination

interface CharacterDatabaseDataSourceInterface {
    suspend fun getFavorites(): List<CharacterEntity>
    suspend fun getNextPageAndCharacters(page: Int): Pair<Boolean, List<CharacterEntity>>
    suspend fun search(text: String): List<CharacterEntity>
    suspend fun getCharacterWith(id: Int): CharacterEntity?
    suspend fun save(characters: List<CharacterEntity>, page: Int)
    suspend fun favOrUnFav(character: CharacterEntity)
}
