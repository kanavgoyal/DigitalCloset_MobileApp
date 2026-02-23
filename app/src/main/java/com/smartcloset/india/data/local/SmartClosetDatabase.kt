package com.smartcloset.india.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smartcloset.india.data.local.converter.Converters
import com.smartcloset.india.data.local.dao.ClosetDao
import com.smartcloset.india.data.local.dao.ClosetLayoutDao
import com.smartcloset.india.data.local.entity.ClosetItemEntity
import com.smartcloset.india.data.local.entity.ClosetLayoutEntity

@Database(entities = [ClosetItemEntity::class, ClosetLayoutEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SmartClosetDatabase : RoomDatabase() {
    abstract fun closetDao(): ClosetDao
    abstract fun layoutDao(): ClosetLayoutDao
}
