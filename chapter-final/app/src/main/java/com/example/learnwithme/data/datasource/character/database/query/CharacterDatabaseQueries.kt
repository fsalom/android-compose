package com.example.learnwithme.data.datasource.character.database.query

import androidx.room.Dao
import androidx.room.RoomDatabase

import com.example.learnwithme.data.datasource.character.database.dbo.CharacterEntity
import com.example.learnwithme.manager.database.BaseDao

@Dao
abstract class CharacterDao(roomDatabase: RoomDatabase) : BaseDao<CharacterEntity>("characters", roomDatabase)