package com.example.learnwithme.data.datasource.character.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.manager.database.BaseEntity

@Entity(tableName = "Characters")
data class CharacterEntity(
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null,
    @ColumnInfo(name = "created_at") val creationDate: Long = System.currentTimeMillis(),
    @PrimaryKey override val id: Int
) : BaseEntity()

fun CharacterEntity.toDomain(): Character =
    Character(
        id = id ?: 0,
        name = name ?: "",
        status = status ?: "",
        species = species ?: "",
        image = image ?: ""
    )