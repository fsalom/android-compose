package com.example.learnwithme.data.datasource.character.remote.disney.dto

import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.CharactersInfoDTO
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.dto.PaginationDTO
import com.example.learnwithme.domain.entity.Pagination
import com.google.gson.annotations.SerializedName

data class DisneyInfoDTO(
    @SerializedName("info") val info: DisneyPaginationDTO? = DisneyPaginationDTO(),
    @SerializedName("data") val data : List<DisneyCharacterDTO> = listOf()
)

data class DisneySingleInfoDTO(
    @SerializedName("info") val info: DisneyPaginationDTO? = DisneyPaginationDTO(),
    @SerializedName("data") val data : DisneyCharacterDTO?
)

data class DisneyPaginationDTO(
    @SerializedName("nextPage") var next : String? = null
)

fun DisneyInfoDTO.toCharactersInfoDTO(): CharactersInfoDTO =
    CharactersInfoDTO(
        info = info?.toPaginationDTO(),
        results = data.map { it.toCharacterDTO() }
    )

fun DisneyPaginationDTO.toPaginationDTO(): PaginationDTO =
    PaginationDTO(
        next = next
    )