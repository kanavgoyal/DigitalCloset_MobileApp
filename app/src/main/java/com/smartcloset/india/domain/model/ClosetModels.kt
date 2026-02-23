package com.smartcloset.india.domain.model

enum class Category {
    UNDERGARMENTS, TOPS, BOTTOMS, BELTS, SOCKS, SHOES, TROUSERS, SHIRTS, T_SHIRTS,
    WARMERS, JACKETS, PULLOVERS, COATS, TIES, PINS, ACCESSORIES, WATCHES, PERFUMES, OTHER
}

enum class Season { SUMMER, MONSOON, WINTER, ALL }
enum class Occasion { CASUAL, OFFICE, ETHNIC, PARTY, TRAVEL, SPORTS, HOME }
enum class Formality { LOW, MEDIUM, HIGH }

data class ClosetItem(
    val id: Long = 0,
    val name: String,
    val category: Category,
    val subCategory: String,
    val colors: List<String>,
    val pattern: String,
    val material: String,
    val season: Season,
    val occasion: Occasion,
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

data class OutfitInput(
    val weatherCelsius: Int? = null,
    val occasion: Occasion,
    val formality: Formality,
    val comfortLevel: Int,
    val colorMood: String,
    val timeOfDay: String,
    val astroColorEnabled: Boolean
)

data class OutfitSuggestion(
    val top: ClosetItem?,
    val bottom: ClosetItem?,
    val footwear: ClosetItem?,
    val layers: List<ClosetItem>,
    val accessories: List<ClosetItem>,
    val confidence: Int,
    val explanation: String
)

data class StorageZone(
    val name: String,
    val type: String,
    val easeScore: Int,
    val maxItems: Int
)
