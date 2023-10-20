package com.example.learnwithme.data.datasource.character.database.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learnwithme.manager.database.BaseEntity

@Entity(tableName = "Characters")
data class CharacterEntity(
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null,
    override val id: Int
) : BaseEntity()