package com.example.learnwithme.data.manager.network

import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import retrofit2.Response

interface  NetworkInterface {
    suspend fun <T> load(call: suspend () -> Response<T>): T
}