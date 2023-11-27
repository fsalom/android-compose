package com.example.learnwithme.data.datasource.character.remote.mock

import com.example.learnwithme.data.datasource.character.remote.CharacterRemoteDataSourceInterface
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharacterDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.PaginationDTO
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.domain.entity.Pagination

class MockCharacterDataSource(
): CharacterRemoteDataSourceInterface {
    val character = CharacterDTO(
        id = 1,
        name = "Personaje de prueba",
        status = "Estado",
        species = "Especie",
        image = "https://qph.cf2.quoracdn.net/main-qimg-e43af1ea0978af7da031068531f8967b-lq")
    val character2 = CharacterDTO(
        id = 2,
        name = "Test",
        status = "Estado",
        species = "Especie",
        image = "https://qph.cf2.quoracdn.net/main-qimg-e43af1ea0978af7da031068531f8967b-lq")
    override suspend fun getPagination(page: Int): CharactersInfoDTO {
        val characters = listOf<CharacterDTO>(
            character, character2
        )

        return CharactersInfoDTO(info = PaginationDTO(next = "true"), results = characters)
    }

    override suspend fun getPaginationFor(text: String, page: Int): CharactersInfoDTO {
        val characters = listOf<CharacterDTO>(
            character, character2
        )

        return CharactersInfoDTO(info = PaginationDTO(next = "true"), results = characters)
    }

    override suspend fun getCharacterWith(id: Int): CharacterDTO? {
        return character
    }
}