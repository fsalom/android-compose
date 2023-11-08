package com.example.learnwithme.data.datasource.character.remote.rickandmorty

import com.example.learnwithme.data.datasource.character.remote.CharacterRemoteDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.api.CharacterApiInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.toDomain
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.data.manager.network.NetworkManager

class RemoteCharactersDataSource(
    private val characterApi: CharacterApiInterface,
    private val network: NetworkManager
): CharacterRemoteDataSourceInterface {

    override suspend fun getPagination(page: Int): Pagination {
        val response = network.load { characterApi.getCharacters(page) }
        return response.toDomain()
    }

    override suspend fun getPaginationFor(text: String, page: Int): Pagination {
        val response = network.load { characterApi.getCharactersFor(text, page) }
        return response.toDomain()
    }

    override suspend fun getCharacterWith(id: Int): Character? {
        val response = network.load { characterApi.getCharacter(id) }
        return response.toDomain()
    }
}