package com.example.learnwithme.data.datasource.character.remote.rickandmorty

import com.example.learnwithme.data.datasource.CharacterDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.api.CharacterApiInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.toDomain
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.manager.NetworkManager

class RemoteCharactersDataSource(
    private val characterApi: CharacterApiInterface,
    private val network: NetworkManager
): CharacterDataSourceInterface {

    override suspend fun getPagination(page: Int): Pagination {
        val response = network.load { characterApi.getCharacters(page) }
        return response.toDomain()
    }

    override suspend fun getCharacterWith(id: String): Character? {
        val response = network.load { characterApi.getCharacter(id) }
        return response.toDomain()
    }
}