package com.example.learnwithme.data.datasource.character.remote.rickandmorty.api

import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiInterface {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharactersInfoDTO>
}