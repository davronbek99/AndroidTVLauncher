package dev.davron.androidtvlauncher

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "launcher_prefs",
        Context.MODE_PRIVATE
    )

    companion object Companion {
        private const val KEY_IS_DEFAULT_LAUNCHER = "is_default_launcher"
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val KEY_LAST_OPENED_APP = "last_opened_app"
        private const val KEY_LAUNCH_COUNT = "launch_count"
    }

    // Launcher default ekanligini saqlash
    var isDefaultLauncher: Boolean
        get() = prefs.getBoolean(KEY_IS_DEFAULT_LAUNCHER, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_DEFAULT_LAUNCHER, value).apply()

    // Birinchi marta ochilganini tekshirish
    var isFirstLaunch: Boolean
        get() = prefs.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) = prefs.edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()

    // Oxirgi ochilgan ilovani saqlash
    var lastOpenedApp: String?
        get() = prefs.getString(KEY_LAST_OPENED_APP, null)
        set(value) = prefs.edit().putString(KEY_LAST_OPENED_APP, value).apply()

    // Launcher necha marta ochilganini hisoblash
    var launchCount: Int
        get() = prefs.getInt(KEY_LAUNCH_COUNT, 0)
        set(value) = prefs.edit().putInt(KEY_LAUNCH_COUNT, value).apply()

    // Launchni increment qilish
    fun incrementLaunchCount() {
        launchCount++
    }

    // Barcha ma'lumotlarni tozalash
    fun clearAll() {
        prefs.edit().clear().apply()
    }
}