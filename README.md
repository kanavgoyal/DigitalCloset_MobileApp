# SmartCloset India (Android)

SmartCloset India is an offline-first Android wardrobe planner for Indian users. It helps you capture clothing quickly, auto-tag essentials, and generate outfit suggestions in minimal taps.

## A) Product Spec (concise)

### Vision
Enable anyone in a family (men, women, kids, elders) to plan what to wear and organize closet space with confidence, while keeping private data local by default.

### Users and Use Cases
- **Busy professionals**: one-tap office outfit suggestions based on weather + formality.
- **Family caretakers**: track kids/elders items and identify never-used clothes.
- **Travelers**: generate quick packing lists.
- **Style explorers**: optional AI prompts (“match this shirt”, “what to buy next”).

### Core Flows
1. **Quick Add**: camera/gallery (placeholder in demo) → auto-tag metadata → save.
2. **Daily Look**: tap Generate → scoring engine picks top/bottom/shoes/layer/accessories + “why this works”.
3. **Storage Helper**: define closet zones and get “what goes where” plan.
4. **AI Settings**: choose Gemini/OpenAI provider + API strategy; photos off by default.

### Features Included
- Digital closet with broad Indian-relevant categories.
- Wear analytics via wear count (least/most/never worn signals).
- Offline-first Room storage.
- Astro color (optional/fun weekday-based local rules).
- LLM provider abstraction with privacy-safe metadata-first posture.

### India-Market Notes
- INR-ready data model (future pricing fields can be added quickly).
- Supports hot, monsoon, mild winter usage via season and weather inputs.
- Basic Hinglish-friendly labels in key screens.

---

## B) Architecture Diagram (text)

```text
[Compose UI]
  ├─ ClosetScreen / RecommendationScreen / StorageScreen / SettingsScreen
  └─ ViewModels (Hilt injected)
         ├─ ClosetViewModel
         │    ├─ GenerateOutfitUseCase
         │    ├─ BuildStoragePlanUseCase
         │    └─ ClosetRepository
         └─ SettingsViewModel
              └─ LlmProvider (OpenAI or Gemini)

[Domain]
  ├─ Models (ClosetItem, OutfitInput, OutfitSuggestion, StorageZone)
  ├─ Repository interfaces
  └─ UseCases + local rules (AstroColorRules)

[Data]
  ├─ Room DB
  │    ├─ ClosetDao
  │    └─ ClosetLayoutDao
  ├─ Repository implementation + mappers
  └─ Retrofit API interface for pluggable LLM assist
```

Patterns used: MVVM + Repository + clean architecture-ish separation (ui/domain/data/di).

---

## C) Project Structure (key files)

- `app/src/main/java/com/smartcloset/india/MainActivity.kt`
- `app/src/main/java/com/smartcloset/india/SmartClosetApplication.kt`
- `app/src/main/java/com/smartcloset/india/data/local/*` (Room DB, entities, DAOs, converters)
- `app/src/main/java/com/smartcloset/india/data/repository/*`
- `app/src/main/java/com/smartcloset/india/domain/model/*`
- `app/src/main/java/com/smartcloset/india/domain/usecase/*`
- `app/src/main/java/com/smartcloset/india/ui/navigation/*`
- `app/src/main/java/com/smartcloset/india/ui/screens/*`
- `app/src/main/java/com/smartcloset/india/ui/viewmodel/*`
- `app/src/main/java/com/smartcloset/india/di/AppModule.kt`

Tests:
- `app/src/test/java/com/smartcloset/india/domain/GenerateOutfitUseCaseTest.kt`
- `app/src/androidTest/java/com/smartcloset/india/data/ClosetDaoTest.kt`

---

## D) Setup Instructions

1. Open in Android Studio Hedgehog+.
2. Ensure JDK 17 and Android SDK 34 installed.
3. Sync Gradle.
4. Run app on emulator/device (min SDK 26).
5. Optional AI setup:
   - Open Settings screen.
   - Select provider (OpenAI or Gemini placeholder).
   - Integrate secure API key entry (recommended via encrypted DataStore in next iteration).

### Notes on AI Privacy
- Default behavior keeps photos local and sends only structured metadata.
- Raw photo sending must be explicit user opt-in.

---

## E) Next Iterations

1. Camera/gallery integration with crop + palette extraction (ML Kit / OpenCV).
2. Cloud backup opt-in (Drive/Firebase).
3. Barcode scan for garments and OCR receipt parsing.
4. Multi-profile support per family member.
5. INR spend analytics + “cost per wear”.
6. Advanced trip planner (days × weather × laundry cycles).
7. Push reminders for seasonal rotation and unworn-item nudges.
8. Marketplace integration (Ajio, Myntra, Amazon Fashion).
9. Better multilingual support (English/Hindi localized resources).
10. On-device personalization model for outfit confidence tuning.
