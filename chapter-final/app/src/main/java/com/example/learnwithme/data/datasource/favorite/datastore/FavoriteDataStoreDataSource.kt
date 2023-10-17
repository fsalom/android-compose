package com.example.learnwithme.data.datasource.favorite.datastore

import com.example.learnwithme.data.datasource.favorite.FavoriteCharacterDataSourceInterface
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.manager.cache.SharedPreferenceManager
import com.example.learnwithme.manager.datastore.DataStoreManager

class FavoriteDataStoreDataSource(
    private val dataStoreManager: DataStoreManager
): FavoriteCharacterDataSourceInterface {
    private val charactersKey = "charactersKey"
    override suspend fun saveThis(character: Character) {
        var characters = getCharacters()
        characters.toMutableList().add(character)
        dataStoreManager.save(characters, key = charactersKey)
    }

    override suspend fun deleteThis(character: Character) {
        var characters: List<Character> = getCharacters()
        characters.toMutableList().removeAll { it.id == character.id }
        dataStoreManager.save(characters, charactersKey)
    }

    override suspend fun getCharacters(): List<Character> {
        var characters = dataStoreManager.retrieve<List<Character>>(charactersKey)
        return  characters ?: emptyList()
    }
}