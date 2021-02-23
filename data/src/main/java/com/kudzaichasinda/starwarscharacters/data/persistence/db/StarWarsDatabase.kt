package com.kudzaichasinda.starwarscharacters.data.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudzaichasinda.starwarscharacters.data.persistence.dao.BookmarkDao
import com.kudzaichasinda.starwarscharacters.data.persistence.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}