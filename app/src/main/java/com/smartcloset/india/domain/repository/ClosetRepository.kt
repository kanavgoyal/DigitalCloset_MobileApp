package com.smartcloset.india.domain.repository

import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.StorageZone
import kotlinx.coroutines.flow.Flow

interface ClosetRepository {
    fun observeItems(): Flow<List<ClosetItem>>
    fun observeNeverWorn(): Flow<List<ClosetItem>>
    fun observeLayoutZones(): Flow<List<StorageZone>>
    suspend fun seedDemoDataIfNeeded()
    suspend fun addItem(item: ClosetItem)
}
