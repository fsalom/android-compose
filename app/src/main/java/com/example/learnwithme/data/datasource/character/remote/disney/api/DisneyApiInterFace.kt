package com.example.learnwithme.data.datasource.character.remote.disney.api
import com.example.learnwithme.data.datasource.character.remote.disney.dto.DisneyInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DisneyApiInterFace {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<DisneyInfoDTO>
}