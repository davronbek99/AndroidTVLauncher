package dev.davron.androidtvlauncher

import android.content.Intent
import android.content.pm.PackageManager

fun loadApps(pm: PackageManager): List<AppInfo> {
    val intent = Intent(Intent.ACTION_MAIN, null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }
    val resolveInfos = pm.queryIntentActivities(intent, 0)


    return resolveInfos.map {
        AppInfo(
            it.activityInfo.packageName,
            it.loadLabel(pm).toString(),
            it.loadIcon(pm)
        )
    }.sortedBy { it.label }
}