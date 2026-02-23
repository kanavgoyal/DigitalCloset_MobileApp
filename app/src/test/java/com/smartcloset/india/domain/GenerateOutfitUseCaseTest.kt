package com.smartcloset.india.domain

import com.smartcloset.india.domain.model.Category
import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.Formality
import com.smartcloset.india.domain.model.Occasion
import com.smartcloset.india.domain.model.OutfitInput
import com.smartcloset.india.domain.model.Season
import com.smartcloset.india.domain.usecase.GenerateOutfitUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GenerateOutfitUseCaseTest {

    private val useCase = GenerateOutfitUseCase()

    @Test
    fun `prefers least worn matching occasion`() {
        val items = listOf(
            item("Office Shirt", Category.SHIRTS, wear = 0, occasion = Occasion.OFFICE, color = "Blue"),
            item("Casual Tee", Category.T_SHIRTS, wear = 4, occasion = Occasion.CASUAL, color = "Blue"),
            item("Black Trousers", Category.TROUSERS, wear = 1, occasion = Occasion.OFFICE, color = "Black"),
            item("Derby Shoes", Category.SHOES, wear = 1, occasion = Occasion.OFFICE, color = "Black")
        )

        val suggestion = useCase.execute(
            items,
            OutfitInput(
                weatherCelsius = 26,
                occasion = Occasion.OFFICE,
                formality = Formality.MEDIUM,
                comfortLevel = 7,
                colorMood = "Blue",
                timeOfDay = "Morning",
                astroColorEnabled = false
            )
        )

        assertEquals("Office Shirt", suggestion.top?.name)
        assertTrue(suggestion.confidence >= 60)
    }

    private fun item(name: String, category: Category, wear: Int, occasion: Occasion, color: String) = ClosetItem(
        name = name,
        category = category,
        subCategory = "",
        colors = listOf(color),
        pattern = "Solid",
        material = "Cotton",
        season = Season.ALL,
        occasion = occasion,
        brand = "Test",
        size = "M",
        fit = "Regular",
        condition = "Good",
        purchaseDate = "2024-01-01",
        lastWornDate = null,
        wearCount = wear,
        tags = emptyList(),
        photoPath = ""
    )
}
