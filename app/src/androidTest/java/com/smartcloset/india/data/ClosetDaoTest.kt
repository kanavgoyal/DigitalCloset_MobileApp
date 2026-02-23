package com.smartcloset.india.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.smartcloset.india.data.local.SmartClosetDatabase
import com.smartcloset.india.data.local.entity.ClosetItemEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClosetDaoTest {

    private lateinit var db: SmartClosetDatabase

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SmartClosetDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndReadNeverWorn() = runBlocking {
        db.closetDao().insert(
            ClosetItemEntity(
                name = "Test Item",
                category = "SHIRTS",
                subCategory = "",
                colors = listOf("Blue"),
                pattern = "Solid",
                material = "Cotton",
                season = "ALL",
                occasion = "OFFICE",
                brand = "Brand",
                size = "M",
                fit = "Regular",
                condition = "New",
                purchaseDate = "2024-01-01",
                lastWornDate = null,
                wearCount = 0,
                tags = listOf("test"),
                photoPath = ""
            )
        )

        val neverWorn = db.closetDao().observeNeverWorn().first()
        assertEquals(1, neverWorn.size)
        assertEquals("Test Item", neverWorn.first().name)
    }
}
