package com.my.remotemediator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.remotemediator.database.dao.ArticleDao
import com.my.remotemediator.database.dao.KeyDao
import com.my.remotemediator.database.entity.ArticleEntity
import com.my.remotemediator.database.entity.KeyEntity


@Database(
    entities = [ArticleEntity::class, KeyEntity::class],
    version = 2,
    exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun keyDao(): KeyDao
}

