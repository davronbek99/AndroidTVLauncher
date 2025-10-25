Android TV Launcher (Jetpack Compose)

Bu loyiha --- **Android TV / Google TV** uchun yozilgan **Custom Home
Launcher** ilovasi.\
Ilova foydalanuvchiga barcha o'rnatilgan dasturlar ro'yxatini ko'rsatadi
va tanlangan ilovani darhol ishga tushiradi.\
U **Jetpack Compose** asosida yozilgan, zamonaviy Material 3
interfeysdan foydalanadi.

------------------------------------------------------------------------

## 🎯 Asosiy funksiyalar

-   ✅ Android tizimi uchun **HOME/LAUNCHER** sifatida ishlaydi\
-   📱 Qurilmadagi barcha **launchable app**larni avtomatik aniqlaydi\
-   🖼️ Har bir ilova uchun **icon** va **nom**ni ko'rsatadi\
-   ▶️ Foydalanuvchi tanlagan ilovani darhol ishga tushiradi
    (`startActivity()`)
-   💾 Holatni `SharedPreferences` yordamida saqlash (bonus variant
    sifatida qo'shilishi mumkin)
-   🌙 Material 3 asosida **Light/Dark mode** qo'llab-quvvatlanadi

------------------------------------------------------------------------

## ⚙️ Texnik talablari

  Parametr                 Qiymat
  ------------------------ ---------------------------
  **minSdkVersion**        21 (Android 5.0 Lollipop)
  **targetSdkVersion**     35 (Android 14)
  **compileSdkVersion**    35
  **Asosiy texnologiya**   Kotlin + Jetpack Compose
  **Gradle versiyasi**     8.1.0
  **Kotlin versiyasi**     1.9.0

------------------------------------------------------------------------

## 🧱 Loyiha tuzilmasi

    app/
     ├─ java/com/example/androidtvlauncher/
     │   ├─ MainActivity.kt
     │   └─ ui/theme/
     │        └─ Theme.kt
     │
     ├─ res/
     │   └─ values/
     │        └─ themes.xml
     │
     ├─ AndroidManifest.xml
     └─ build.gradle

------------------------------------------------------------------------

## 🪄 Asosiy kutubxonalar

``` gradle
implementation "androidx.core:core-ktx:1.15.0"
implementation "androidx.activity:activity-compose:1.10.1"
implementation "androidx.compose.ui:ui:1.6.0"
implementation "androidx.compose.material3:material3:1.2.0"
implementation "androidx.compose.ui:ui-tooling-preview:1.6.0"
debugImplementation "androidx.compose.ui:ui-tooling:1.6.0"
```

------------------------------------------------------------------------

### 1️⃣ Loyiha tayyorlash

1.  Android Studio'da **"Empty Compose Activity"** shabloni asosida
    yangi loyiha yarating.\
2.  Yuqoridagi kodlarni mos fayllarga joylashtiring.\
3.  `Gradle Sync` yakunlanishini kuting.

### 2️⃣ Qurish

-   Android Studio menyusida:\
    **Build → Make Project** yoki **Run → Run 'app'**
-   APK fayl avtomatik `app/build/outputs/apk/debug/` papkasida
    yaratiladi.

### 3️⃣ Qurilmada sinash

-   APK'ni Android TV yoki Google TV qurilmangizga o'rnating.
-   Ilovani ishga tushiring → `HOME` tugmasini bosing.\
-   Default launcher sifatida **Android TV Launcher**ni tanlang.
-   Endi ilova avtomatik ravishda barcha dasturlar ro'yxatini chiqaradi.

------------------------------------------------------------------------

## 🧩 Ishlash prinsipi

-   Ilova `PackageManager` orqali qurilmadagi barcha `CATEGORY_LAUNCHER`
    ilovalarni oladi.\
-   Har bir ilova uchun `label` va `icon` olinadi.\
-   Compose `LazyColumn` orqali ro'yxat tarzida chiqariladi.\
-   Har bir elementga `clickable` hodisasi bog'langan --- foydalanuvchi
    bosganda `Intent` orqali ilova ishga tushadi.

------------------------------------------------------------------------

## 🧠 Qo'shimcha imkoniyatlar (Bonus)

-   📺 **Leanback kutubxonasi** bilan integratsiya (TV remote uchun
    optimizatsiya)
-   💾 So'nggi tanlangan ilovani `SharedPreferences`da saqlash
-   🎨 Ikonalar hajmi va ro'yxat joylashuvini moslashtirish (TV UI uchun
    qulay)

------------------------------------------------------------------------

## 🧑‍💻 Muallif haqida

**Developer:** Davronbek Sherg'oziyev
**Texnologiyalar:** Kotlin • Jetpack Compose • MVVM • Android TV
**Test topshiriq:** Android TV Launcher --- Compose versiyasi (2025)

------------------------------------------------------------------------

## 📹 Video demosi

> 🎥 Ishlayotgan video yozuv topshiruvga ilova qilingan bo'lishi kerak.\
> (Ekran yozish uchun tavsiya: **OBS Studio** yoki **Android Studio
> Emulator Recorder**.)

------------------------------------------------------------------------

## 🧾 Litsenziya

Erkin ravishda o'zgartirish, qo'shish yoki o'rganish uchun
ishlatishingiz mumkin.
