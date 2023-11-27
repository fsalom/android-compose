package com.example.learnwithme.data.datasource.character.remote.rickandmorty

import com.example.learnwithme.data.datasource.character.remote.CharacterRemoteDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.api.CharacterApiInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharacterDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import com.example.learnwithme.data.manager.network.NetworkInterface
import retrofit2.Retrofit

class RemoteCharactersDataSource(
    private val network: NetworkInterface,
    private val retrofit: Retrofit,
): CharacterRemoteDataSourceInterface {

    private val characterApi: CharacterApiInterface = retrofit.create(CharacterApiInterface::class.java)

    override suspend fun getPagination(page: Int): CharactersInfoDTO {
        return network.load { characterApi.getCharacters(page) }
    }

    override suspend fun getPaginationFor(text: String, page: Int): CharactersInfoDTO {
        return network.load { characterApi.getCharactersFor(text, page) }
    }

    override suspend fun getCharacterWith(id: Int): CharacterDTO? {
        return network.load { characterApi.getCharacter(id) }
    }
}