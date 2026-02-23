package com.smartcloset.india.domain.usecase

import com.smartcloset.india.domain.model.Category
import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.Formality
import com.smartcloset.india.domain.model.Occasion
import com.smartcloset.india.domain.model.OutfitInput
import com.smartcloset.india.domain.model.OutfitSuggestion
import com.smartcloset.india.domain.model.Season
import kotlin.math.max
import javax.inject.Inject

class GenerateOutfitUseCase @Inject constructor() {

    fun execute(items: List<ClosetItem>, input: OutfitInput): OutfitSuggestion {
        val tops = items.filter { it.category in setOf(Category.TOPS, Category.SHIRTS, Category.T_SHIRTS) }
        val bottoms = items.filter { it.category in setOf(Category.BOTTOMS, Category.TROUSERS) }
        val shoes = items.filter { it.category == Category.SHOES }
        val layers = items.filter { it.category in setOf(Category.JACKETS, Category.COATS, Category.PULLOVERS, Category.WARMERS) }
        val accessories = items.filter { it.category in setOf(Category.ACCESSORIES, Category.WATCHES, Category.TIES, Category.PINS, Category.BELTS, Category.PERFUMES) }

        val pickedTop = tops.maxByOrNull { scoreItem(it, input) }
        val pickedBottom = bottoms.maxByOrNull { scoreItem(it, input) }
        val pickedShoes = shoes.maxByOrNull { scoreItem(it, input) }
        val pickedLayer = layers.sortedByDescending { scoreItem(it, input) }.take(if ((input.weatherCelsius ?: 30) < 20) 1 else 0)
        val pickedAccessories = accessories.sortedByDescending { scoreItem(it, input) }.take(2)

        val totalScore = listOfNotNull(pickedTop, pickedBottom, pickedShoes).sumOf { scoreItem(it, input) }
        val confidence = (totalScore / 3).coerceIn(0, 100)

        return OutfitSuggestion(
            top = pickedTop,
            bottom = pickedBottom,
            footwear = pickedShoes,
            layers = pickedLayer,
            accessories = pickedAccessories,
            confidence = confidence,
            explanation = buildExplanation(input, confidence)
        )
    }

    private fun scoreItem(item: ClosetItem, input: OutfitInput): Int {
        var score = 50
        if (item.occasion == input.occasion) score += 20
        if (item.wearCount == 0) score += 12 else score += max(0, 10 - item.wearCount)
        if (matchesFormality(item, input.formality)) score += 10
        if (matchesSeason(item, input.weatherCelsius)) score += 8
        if (item.colors.any { it.contains(input.colorMood, ignoreCase = true) }) score += 8

        if (input.astroColorEnabled) {
            val astroColors = AstroColorRules.forToday()
            if (item.colors.any { astroColors.contains(it.lowercase()) }) score += 5
        }
        return score
    }

    private fun matchesFormality(item: ClosetItem, formality: Formality): Boolean = when (formality) {
        Formality.LOW -> item.category in setOf(Category.T_SHIRTS, Category.SHOES, Category.ACCESSORIES)
        Formality.MEDIUM -> item.category !in setOf(Category.UNDERGARMENTS)
        Formality.HIGH -> item.occasion in setOf(Occasion.OFFICE, Occasion.PARTY, Occasion.ETHNIC)
    }

    private fun matchesSeason(item: ClosetItem, temp: Int?): Boolean {
        val weather = temp ?: 28
        return when {
            weather <= 20 -> item.season in setOf(Season.WINTER, Season.ALL)
            weather > 32 -> item.season in setOf(Season.SUMMER, Season.ALL)
            else -> true
        }
    }

    private fun buildExplanation(input: OutfitInput, confidence: Int): String {
        return "Picked to match ${input.occasion.name.lowercase()} vibe, ${input.formality.name.lowercase()} formality, " +
            "and low-repeat priority. Confidence $confidence%. Astro feature is ${if (input.astroColorEnabled) "on" else "off"}. " +
            "Fun styling aid only; not medical or financial advice."
    }
}
