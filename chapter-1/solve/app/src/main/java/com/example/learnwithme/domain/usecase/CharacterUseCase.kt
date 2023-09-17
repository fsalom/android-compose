package com.example.learnwithme.domain.usecase

import com.example.learnwithme.data.repository.CharacterRepositoryInterface
import com.example.learnwithme.domain.entity.Character

class CharacterUseCase(): CharacterUseCaseInterface {
    override suspend fun getNextPageAndCharacters(
        page: Int
    ): Pair<Boolean, List<Character>> {
        return Pair(false, emptyList())
    }
}