package dev.davron.androidtvlauncher

import android.app.Activity
import android.app.ComponentCaller
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import dev.davron.androidtvlauncher.ui.theme.AndroidTVLauncherTheme

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roleManager = getSystemService(RoleManager::class.java)
        if (!roleManager.isRoleHeld(RoleManager.ROLE_HOME)) {
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_HOME)
            startActivityForResult(intent, 1)
        }

        setContent {
            AndroidTVLauncherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TvLauncherApp()
                }
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Launcher tanlandi ✅", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tanlanmadi ❌", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
//
//@Composable
//fun TvLauncherApp() {
//    val context = androidx.compose.ui.platform.LocalContext.current
//    val packageManager = context.packageManager
//    val apps = remember { getInstalledApps(packageManager) }
//
//    // 🔒 Back tugmasini bloklaymiz
//    BackHandler {
//        // hech narsa qilmaymiz
//    }
//
//    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//        Column(
//            modifier = Modifier.fillMaxSize().padding(32.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text("📺 My TV Home Launcher", style = MaterialTheme.typography.headlineSmall)
//            Spacer(Modifier.height(24.dp))
//
//            LazyColumn(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                items(apps.size) { index ->
//                    val app = apps[index]
//                    Button(
//                        onClick = {
//                            val intent = packageManager.getLaunchIntentForPackage(app.packageName)
//                            if (intent != null) {
//                                context.startActivity(intent)
//                            }
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth(0.7f)
//                            .padding(8.dp)
//                    ) {
//                        Text(app.appName)
//                    }
//                }
//            }
//        }
//    }
//}
//
//data class AppInfo(val appName: String, val packageName: String)
//
//fun getInstalledApps(pm: PackageManager): List<AppInfo> {
//    val apps = mutableListOf<AppInfo>()
//    val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
//    for (packageInfo in packages) {
//        val label = pm.getApplicationLabel(packageInfo).toString()
//        val pkg = packageInfo.packageName
//        if (pm.getLaunchIntentForPackage(pkg) != null && pkg != "com.example.tvlauncher") {
//            apps.add(AppInfo(label, pkg))
//        }
//    }
//    return apps.sortedBy { it.appName }
//}