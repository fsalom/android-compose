package com.example.learnwithme.data.datasource.favorite.cache

import android.content.SharedPreferences
import com.example.learnwithme.data.datasource.favorite.FavoriteCharacterDataSourceInterface
import com.example.learnwithme.domain.entity.Character

class FavoriteCacheDataSource(sharedPreferences: SharedPreferences): FavoriteCharacterDataSourceInterface {
    override suspend fun saveThis(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteThis(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(): List<Character> {
        TODO("Not yet implemented")
    }
}