package com.smartcloset.india.domain.usecase

import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.StorageZone
import javax.inject.Inject

class BuildStoragePlanUseCase @Inject constructor() {
    fun execute(items: List<ClosetItem>, zones: List<StorageZone>): Map<String, List<String>> {
        val sortedZones = zones.sortedByDescending { it.easeScore }
        val daily = items.sortedByDescending { it.wearCount }.take(10)
        val seasonal = items.filter { it.wearCount <= 1 }
        val delicate = items.filter { it.material.contains("silk", true) || it.material.contains("wool", true) }

        return mapOf(
            (sortedZones.getOrNull(0)?.name ?: "Easy Access") to daily.map { it.name },
            (sortedZones.lastOrNull()?.name ?: "Top Shelf") to seasonal.map { it.name },
            (sortedZones.find { it.type.contains("Drawer", true) }?.name ?: "Drawer") to delicate.map { it.name }
        )
    }
}
