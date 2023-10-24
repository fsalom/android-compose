package com.example.learnwithme.data.datasource.favorite.database

import androidx.room.RoomDatabase
import com.example.learnwithme.data.datasource.character.database.dbo.CharacterEntity
import com.example.learnwithme.data.datasource.character.database.dbo.toDomain
import com.example.learnwithme.data.datasource.character.database.query.CharacterDao
import com.example.learnwithme.data.datasource.favorite.FavoriteCharacterDataSourceInterface
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.manager.database.BaseDao
import com.example.learnwithme.manager.datastore.DataStoreManager

class FavoriteDataBaseDataSource(
    val dao: CharacterDao
): FavoriteCharacterDataSourceInterface {
    private val charactersKey = "charactersKey2"


    override suspend fun saveThis(character: Character) {
        var characterToInsert = CharacterEntity(
            name = character.name,
            status = character.status,
            species = character.species,
            image = character.image,
            id = character.id
        )
        dao.insert(characterToInsert)
    }

    override suspend fun deleteThis(character: Character) {
        var characterToDelete = CharacterEntity(
            name = character.name,
            status = character.status,
            species = character.species,
            image = character.image,
            id = character.id
        )
        dao.delete(characterToDelete)
    }

    override suspend fun getCharacters(): List<Character> {
        val entityCharacters = dao.getAll()
        val characters = entityCharacters.map {
            it.toDomain()
        }
        return characters
    }
}