package com.example.learnwithme.data.datasource.character.remote.disney

import com.example.learnwithme.data.datasource.character.remote.CharacterRemoteDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.disney.api.DisneyApiInterFace
import com.example.learnwithme.data.datasource.character.remote.disney.dto.toCharacterDTO
import com.example.learnwithme.data.datasource.character.remote.disney.dto.toCharactersInfoDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharacterDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import com.example.learnwithme.data.manager.network.NetworkManager

class RemoteDisneyCharactersDataSource(
    private val disneyApi: DisneyApiInterFace,
    private val network: NetworkManager
): CharacterRemoteDataSourceInterface {

    override suspend fun getPagination(page: Int): CharactersInfoDTO {
        return network.load { disneyApi.getCharacters(page) }.toCharactersInfoDTO()
    }

    override suspend fun getPaginationFor(text: String, page: Int): CharactersInfoDTO {
        return network.load { disneyApi.getCharactersFor(text, page) }.toCharactersInfoDTO()
    }

    override suspend fun getCharacterWith(id: Int): CharacterDTO? {
        return network.load { disneyApi.getCharacter(id) }.data?.toCharacterDTO()
    }
}