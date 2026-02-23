package com.smartcloset.india.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartcloset.india.domain.model.Category
import com.smartcloset.india.domain.model.ClosetItem
import com.smartcloset.india.domain.model.Formality
import com.smartcloset.india.domain.model.Occasion
import com.smartcloset.india.domain.model.OutfitInput
import com.smartcloset.india.domain.model.OutfitSuggestion
import com.smartcloset.india.domain.model.Season
import com.smartcloset.india.domain.repository.ClosetRepository
import com.smartcloset.india.domain.usecase.BuildStoragePlanUseCase
import com.smartcloset.india.domain.usecase.GenerateOutfitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@HiltViewModel
class ClosetViewModel @Inject constructor(
    private val repository: ClosetRepository,
    private val generateOutfitUseCase: GenerateOutfitUseCase,
    private val buildStoragePlanUseCase: BuildStoragePlanUseCase
) : ViewModel() {

    private val _items = MutableStateFlow<List<ClosetItem>>(emptyList())
    val items: StateFlow<List<ClosetItem>> = _items.asStateFlow()

    private val _suggestion = MutableStateFlow<OutfitSuggestion?>(null)
    val suggestion: StateFlow<OutfitSuggestion?> = _suggestion.asStateFlow()

    private val _storagePlan = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val storagePlan: StateFlow<Map<String, List<String>>> = _storagePlan.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedDemoDataIfNeeded()
            repository.observeItems().collect { _items.value = it }
        }
        viewModelScope.launch {
            combine(repository.observeItems(), repository.observeLayoutZones()) { items, zones ->
                buildStoragePlanUseCase.execute(items, zones)
            }.collect { _storagePlan.value = it }
        }
    }

    fun quickAddDemo() {
        viewModelScope.launch {
            repository.addItem(
                ClosetItem(
                    name = "Quick Added Tee",
                    category = Category.T_SHIRTS,
                    subCategory = "Crew Neck",
                    colors = listOf("White"),
                    pattern = "Solid",
                    material = "Cotton",
                    season = Season.SUMMER,
                    occasion = Occasion.CASUAL,
                    brand = "Local",
                    size = "L",
                    fit = "Relaxed",
                    condition = "New",
                    purchaseDate = "2024-08-01",
                    lastWornDate = null,
                    wearCount = 0,
                    tags = listOf("quick-add", "auto-tag:white", "auto-crop:yes"),
                    photoPath = "",
                )
            )
        }
    }

    fun generateDailyOutfit(occasion: Occasion = Occasion.OFFICE, astroOn: Boolean = false) {
        _suggestion.value = generateOutfitUseCase.execute(
            _items.value,
            OutfitInput(
                weatherCelsius = 29,
                occasion = occasion,
                formality = Formality.MEDIUM,
                comfortLevel = 7,
                colorMood = "Blue",
                timeOfDay = "Morning",
                astroColorEnabled = astroOn
            )
        )
    }
}
