package com.smartcloset.india.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "closet_items")
data class ClosetItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val category: String,
    val subCategory: String,
    val colors: List<String>,
    val pattern: String,
    val material: String,
    val season: String,
    val occasion: String,
    val brand: String,
    val size: String,
    val fit: String,
    val condition: String,
    val purchaseDate: String,
    val lastWornDate: String?,
    val wearCount: Int,
    val tags: List<String>,
    val photoPath: String,
    val isFavorite: Boolean = false
)
