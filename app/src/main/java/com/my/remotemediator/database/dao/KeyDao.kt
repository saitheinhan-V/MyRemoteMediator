package com.my.remotemediator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.remotemediator.database.entity.KeyEntity

@Dao
interface KeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<KeyEntity>)

    @Query("SELECT * FROM ${KeyEntity.TABLE_NAME} WHERE id =:id")
    suspend fun getKey(id: Int) : KeyEntity?

    @Query("DELETE FROM ${KeyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT `created_at` FROM ${KeyEntity.TABLE_NAME} ORDER BY `created_at` DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}