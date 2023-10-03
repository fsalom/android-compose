package com.example.learnwithme.data.datasource.favorite

import com.example.learnwithme.domain.entity.Character

interface FavoriteCharacterDataSourceInterface {
    suspend fun saveThis(character: Character)
    suspend fun deleteThis(character: Character)
    suspend fun getCharacters(): List<Character>
}
