package com.example.learnwithme.data.datasource.character.remote.rickandmorty

import com.example.learnwithme.data.datasource.character.CharacterDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.api.CharacterApiInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.toDomain
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.manager.NetworkManager

class RemoteCharactersDataSource(
    private val characterApi: CharacterApiInterface,
    private val network: NetworkManager
): CharacterDataSourceInterface {

    override suspend fun getPagination(page: Int): Pagination {
        val response = network.load { characterApi.getCharacters(page) }
        return response.toDomain()
    }
}