package com.example.learnwithme.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learnwithme.data.datasource.character.database.query.CharacterDao

@Database(
    entities = [CharacterDao::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}