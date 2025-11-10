package dev.davron.androidtvlauncher

import android.R.attr.clickable
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TVLauncherScreen() {
    val context = LocalContext.current
    val packageManager = context.packageManager

    // 📦 O‘rnatilgan launchable ilovalarni olish
    val apps = remember {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        packageManager.queryIntentActivities(intent, 0)
            .sortedBy { it.loadLabel(packageManager).toString() }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📺 Android TV Launcher",
                color = Color.White,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(apps) { app ->
                    AppItem(app)
                }
            }
        }
    }
}

@Composable
fun AppItem(appInfo: ResolveInfo) {
    val context = LocalContext.current
    val pm = context.packageManager
    val label = appInfo.loadLabel(pm).toString()
    val icon = appInfo.loadIcon(pm)

    Column(
        modifier = Modifier
            .width(120.dp)
            .focusable(true)
            .background(Color.DarkGray)
            .padding(8.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = rememberDrawablePainter(drawable = icon),
            contentDescription = label,
            modifier = Modifier.size(64.dp),
            tint = Color.Unspecified
        )
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

    // 📲 Bosilganda ilovani ishga tushurish
   clickable {
        val launchIntent =
            pm.getLaunchIntentForPackage(appInfo.activityInfo.packageName)
        if (launchIntent != null) {
            context.startActivity(launchIntent)
        }
    }
}