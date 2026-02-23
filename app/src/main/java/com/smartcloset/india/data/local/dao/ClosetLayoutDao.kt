package com.smartcloset.india.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smartcloset.india.data.local.entity.ClosetLayoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClosetLayoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZones(zones: List<ClosetLayoutEntity>)

    @Query("SELECT * FROM closet_layout")
    fun observeZones(): Flow<List<ClosetLayoutEntity>>
}
