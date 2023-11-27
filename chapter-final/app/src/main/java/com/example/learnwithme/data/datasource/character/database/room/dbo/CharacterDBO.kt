package com.example.learnwithme.data.datasource.character.database.room.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learnwithme.domain.entity.Character
import com.example.learnwithme.data.manager.database.BaseEntity

@Entity(tableName = "characters")
data class CharacterEntity(
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null,
    var isFavorite: Boolean = false,
    val page: Int = 0,
    @ColumnInfo(name = "created_at") val creationDate: Long = System.currentTimeMillis(),
    @PrimaryKey override val id: Int
) : BaseEntity() {
}