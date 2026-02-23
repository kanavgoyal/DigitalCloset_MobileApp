package com.smartcloset.india.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smartcloset.india.data.local.entity.ClosetItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClosetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ClosetItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ClosetItemEntity>)

    @Query("SELECT * FROM closet_items ORDER BY wearCount ASC")
    fun observeAll(): Flow<List<ClosetItemEntity>>

    @Query("SELECT * FROM closet_items WHERE wearCount = 0")
    fun observeNeverWorn(): Flow<List<ClosetItemEntity>>

    @Query("SELECT * FROM closet_items ORDER BY wearCount DESC LIMIT 20")
    suspend fun getMostWorn(): List<ClosetItemEntity>

    @Query("DELETE FROM closet_items")
    suspend fun clear()
}
