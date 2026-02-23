package com.smartcloset.india.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "closet_layout")
data class ClosetLayoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val zoneName: String,
    val zoneType: String,
    val easeScore: Int,
    val maxItems: Int
)
