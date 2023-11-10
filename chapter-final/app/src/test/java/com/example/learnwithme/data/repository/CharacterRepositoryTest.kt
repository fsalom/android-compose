package com.example.learnwithme.data.repository

import com.example.learnwithme.data.datasource.character.remote.rickandmorty.RemoteCharactersDataSource
import com.example.learnwithme.data.repository.character.CharacterRepository
import org.junit.Test

class CharacterRepositoryTest {
    @Test
    fun doAction_getHasNextPageAndCharacters(){
        /* Given */
        val repositoryTest: CharacterRepository(remo)
        val mock = mock<RemoteCharactersDataSource> {
            on { getText() } doReturn "text"
        }

        /* When */
        classUnderTest.doAction()

        /* Then */
        verify(mock).doSomething(any())
    }
}