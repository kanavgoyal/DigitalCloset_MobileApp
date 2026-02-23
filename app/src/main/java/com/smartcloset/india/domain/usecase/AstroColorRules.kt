package com.smartcloset.india.domain.usecase

import java.time.DayOfWeek
import java.time.LocalDate

object AstroColorRules {
    private val dayColors = mapOf(
        DayOfWeek.MONDAY to listOf("white", "silver", "cream"),
        DayOfWeek.TUESDAY to listOf("red", "maroon", "rust"),
        DayOfWeek.WEDNESDAY to listOf("green", "olive", "mint"),
        DayOfWeek.THURSDAY to listOf("yellow", "mustard", "gold"),
        DayOfWeek.FRIDAY to listOf("pink", "pastel", "teal"),
        DayOfWeek.SATURDAY to listOf("black", "navy", "indigo"),
        DayOfWeek.SUNDAY to listOf("orange", "peach", "white")
    )

    fun forToday(date: LocalDate = LocalDate.now()): List<String> = dayColors[date.dayOfWeek].orEmpty()
}
