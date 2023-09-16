package com.example.learnwithme.domain.usecase

import com.example.learnwithme.data.repository.CharacterRepositoryInterface
import com.example.learnwithme.domain.entity.Character

class CharacterUseCase(val repository: CharacterRepositoryInterface): CharacterUseCaseInterface {
    override suspend fun getNextPageAndCharacters(
        page: Int
    ): Pair<Boolean, List<Character>> {
        var pagination = repository.getPagination(page)
        return Pair(pagination.hasNextPage, pagination.characters)
    }
}