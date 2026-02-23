package com.smartcloset.india.data.repository

import com.smartcloset.india.data.local.entity.ClosetItemEntity
import com.smartcloset.india.data.local.entity.ClosetLayoutEntity
import com.smartcloset.india.domain.model.Category
import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.Occasion
import com.smartcloset.india.domain.model.Season
import com.smartcloset.india.domain.model.StorageZone

fun ClosetItemEntity.toDomain() = ClosetItem(
    id = id,
    name = name,
    category = Category.valueOf(category),
    subCategory = subCategory,
    colors = colors,
    pattern = pattern,
    material = material,
    season = Season.valueOf(season),
    occasion = Occasion.valueOf(occasion),
    brand = brand,
    size = size,
    fit = fit,
    condition = condition,
    purchaseDate = purchaseDate,
    lastWornDate = lastWornDate,
    wearCount = wearCount,
    tags = tags,
    photoPath = photoPath,
    isFavorite = isFavorite
)

fun ClosetItem.toEntity() = ClosetItemEntity(
    id = id,
    name = name,
    category = category.name,
    subCategory = subCategory,
    colors = colors,
    pattern = pattern,
    material = material,
    season = season.name,
    occasion = occasion.name,
    brand = brand,
    size = size,
    fit = fit,
    condition = condition,
    purchaseDate = purchaseDate,
    lastWornDate = lastWornDate,
    wearCount = wearCount,
    tags = tags,
    photoPath = photoPath,
    isFavorite = isFavorite
)

fun ClosetLayoutEntity.toDomain() = StorageZone(zoneName, zoneType, easeScore, maxItems)
