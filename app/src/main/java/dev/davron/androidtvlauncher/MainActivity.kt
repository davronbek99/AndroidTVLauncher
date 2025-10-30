package dev.davron.androidtvlauncher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.davron.androidtvlauncher.ui.theme.AndroidTVLauncherTheme

class MainActivity : ComponentActivity() {

    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SharedPreferences ni boshlash
        mySharedPreferences = MySharedPreferences(this)

        // Launcher statusini tekshirish
        checkLauncherStatus()

        // Birinchi marta ochilganini tekshirish
        if (mySharedPreferences.isFirstLaunch) {
            Log.d("Launcher", "Bu birinchi marta ochilmoqda!")
            mySharedPreferences.isFirstLaunch = false
        }

        // Launch countni oshirish
        mySharedPreferences.incrementLaunchCount()
        Log.d("Launcher", "Launcher ${mySharedPreferences.launchCount} marta ochildi")


        enableEdgeToEdge()
        setContent {
            AndroidTVLauncherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppListScreen(
                        packageManager,
                        onAppOpened = { packageName ->
                            // Ochilgan ilovani saqlash
                            mySharedPreferences.lastOpenedApp = packageName
                            Log.d("Launcher", "Oxirgi ochilgan ilova: $packageName")
                        })
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Har safar launcher ga qaytganda statusni yangilash
        checkLauncherStatus()
    }

    private fun checkLauncherStatus() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
        }

        val resolveInfo = packageManager.resolveActivity(
            intent,
            0
        )

        val isDefault = resolveInfo?.activityInfo?.packageName == packageName
        mySharedPreferences.isDefaultLauncher = isDefault

        Log.d("Launcher", "Default launcher: $isDefault")
        if (mySharedPreferences.lastOpenedApp != null) {
            Log.d("Launcher", "Oxirgi ochilgan: ${mySharedPreferences.lastOpenedApp}")
        }
    }
}