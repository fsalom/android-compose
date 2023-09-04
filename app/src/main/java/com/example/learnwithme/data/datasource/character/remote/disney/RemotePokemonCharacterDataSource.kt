package com.example.learnwithme.data.datasource.character.remote.disney

import com.example.learnwithme.data.datasource.CharacterDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.disney.api.DisneyApiInterFace

import com.example.learnwithme.data.datasource.character.remote.disney.dto.toDomain
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.toDomain
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.domain.entity.Pagination
import com.example.learnwithme.manager.NetworkManager

class RemoteDisneyCharactersDataSource(
    private val disneyApi: DisneyApiInterFace,
    private val network: NetworkManager
): CharacterDataSourceInterface {

    override suspend fun getPagination(page: Int): Pagination {
        val response = network.load { disneyApi.getCharacters(page) }
        return response.toDomain()
    }

    override suspend fun getCharacterWith(id: String): Character? {
        val response = network.load { disneyApi.getCharacter(id) }
        val name = response.name ?: return null
        return response.toDomain()
    }
}