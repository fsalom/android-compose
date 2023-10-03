package com.example.learnwithme.data.datasource.character.database.query

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnwithme.data.datasource.character.database.dbo.CharacterDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDatabaseQueries {

    @Query("SELECT * FROM Characters")
    fun observeCharacters(): Flow<List<CharacterDBO>>

    @Query("SELECT * FROM Characters")
    suspend fun getCharacters(): List<CharacterDBO>

    @Query("SELECT * FROM Characters WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterDBO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDBO)

    @Delete
    suspend fun deleteCharacter(character: CharacterDBO)

    @Query("DELETE FROM Characters")
    suspend fun deleteCharacters()
}