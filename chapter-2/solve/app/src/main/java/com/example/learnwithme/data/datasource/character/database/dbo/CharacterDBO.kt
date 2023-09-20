package com.example.learnwithme.data.datasource.character.database.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class CharacterDBO(
    @PrimaryKey val id: Int,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null
)