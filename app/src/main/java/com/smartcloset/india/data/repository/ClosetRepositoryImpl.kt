package com.smartcloset.india.data.repository

import com.smartcloset.india.data.local.dao.ClosetDao
import com.smartcloset.india.data.local.dao.ClosetLayoutDao
import com.smartcloset.india.data.local.entity.ClosetItemEntity
import com.smartcloset.india.data.local.entity.ClosetLayoutEntity
import com.smartcloset.india.domain.model.Category
import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.Occasion
import com.smartcloset.india.domain.model.Season
import com.smartcloset.india.domain.model.StorageZone
import com.smartcloset.india.domain.repository.ClosetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClosetRepositoryImpl @Inject constructor(
    private val closetDao: ClosetDao,
    private val layoutDao: ClosetLayoutDao
) : ClosetRepository {

    override fun observeItems(): Flow<List<ClosetItem>> = closetDao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeNeverWorn(): Flow<List<ClosetItem>> = closetDao.observeNeverWorn().map { it.map { entity -> entity.toDomain() } }

    override fun observeLayoutZones(): Flow<List<StorageZone>> = layoutDao.observeZones().map { it.map { zone -> zone.toDomain() } }

    override suspend fun seedDemoDataIfNeeded() {
        val mostWorn = closetDao.getMostWorn()
        if (mostWorn.isNotEmpty()) return

        closetDao.insertAll(
            listOf(
                demoItem("Blue Oxford Shirt", Category.SHIRTS, listOf("Blue"), Occasion.OFFICE, wearCount = 2),
                demoItem("Black Chinos", Category.TROUSERS, listOf("Black"), Occasion.OFFICE, wearCount = 1),
                demoItem("White Sneakers", Category.SHOES, listOf("White"), Occasion.CASUAL, wearCount = 4),
                demoItem("Navy Blazer", Category.JACKETS, listOf("Navy"), Occasion.OFFICE, wearCount = 0),
                demoItem("Silver Watch", Category.WATCHES, listOf("Silver"), Occasion.OFFICE, wearCount = 5),
                demoItem("Maroon Kurta", Category.TOPS, listOf("Maroon"), Occasion.ETHNIC, wearCount = 0),
                demoItem("Beige Loafers", Category.SHOES, listOf("Beige"), Occasion.PARTY, wearCount = 1)
            )
        )
        layoutDao.insertZones(
            listOf(
                ClosetLayoutEntity(zoneName = "Daily Shelf", zoneType = "Shelf", easeScore = 10, maxItems = 15),
                ClosetLayoutEntity(zoneName = "Top Drawer", zoneType = "Drawer", easeScore = 7, maxItems = 20),
                ClosetLayoutEntity(zoneName = "Hanging Left", zoneType = "Hanger", easeScore = 9, maxItems = 12),
                ClosetLayoutEntity(zoneName = "Seasonal Top", zoneType = "Shelf", easeScore = 4, maxItems = 25)
            )
        )
    }

    override suspend fun addItem(item: ClosetItem) {
        closetDao.insert(item.toEntity())
    }

    private fun demoItem(
        name: String,
        category: Category,
        colors: List<String>,
        occasion: Occasion,
        wearCount: Int
    ) = ClosetItemEntity(
        name = name,
        category = category.name,
        subCategory = "General",
        colors = colors,
        pattern = "Solid",
        material = "Cotton",
        season = Season.ALL.name,
        occasion = occasion.name,
        brand = "Demo",
        size = "M",
        fit = "Regular",
        condition = "Good",
        purchaseDate = "2024-01-10",
        lastWornDate = null,
        wearCount = wearCount,
        tags = listOf("demo", "quick-add"),
        photoPath = "",
        isFavorite = wearCount > 3
    )
}
