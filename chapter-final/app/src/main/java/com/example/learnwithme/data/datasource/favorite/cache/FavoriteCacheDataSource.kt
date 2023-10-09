package com.example.learnwithme.data.datasource.favorite.cache

import com.example.learnwithme.data.datasource.favorite.FavoriteCharacterDataSourceInterface
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.manager.cache.SharedPreferenceManager

class FavoriteCacheDataSource(private val sharedPreferenceManager: SharedPreferenceManager): FavoriteCharacterDataSourceInterface {
    private val charactersKey = "charactersKey"
    override suspend fun saveThis(character: Character) {
        var characters = getCharacters()
        characters.toMutableList().add(character)
        sharedPreferenceManager.save(characters, charactersKey)
    }

    override suspend fun deleteThis(character: Character) {
        var characters: List<Character> = getCharacters()
        characters.toMutableList().removeAll { it.id == character.id }
        sharedPreferenceManager.save(characters, charactersKey)
    }

    override suspend fun getCharacters(): List<Character> {
        var characters = sharedPreferenceManager.retrieve<List<Character>>(charactersKey)
        return  characters ?: emptyList()
    }
}