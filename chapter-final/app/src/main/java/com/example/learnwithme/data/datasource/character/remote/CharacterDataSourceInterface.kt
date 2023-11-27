package com.example.learnwithme.data.datasource.character.remote

import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharacterDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO

interface CharacterRemoteDataSourceInterface {
    suspend fun getPagination(page: Int): CharactersInfoDTO
    suspend fun getPaginationFor(text: String, page: Int): CharactersInfoDTO
    suspend fun getCharacterWith(id: Int): CharacterDTO?
}
