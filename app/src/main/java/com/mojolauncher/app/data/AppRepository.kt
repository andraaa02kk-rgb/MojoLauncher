package com.mojolauncher.app.data

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.mojolauncher.app.models.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val context: Context) {
    private val packageManager = context.packageManager

    suspend fun getInstalledApps(): List<AppInfo> = withContext(Dispatchers.Default) {
        val apps = mutableListOf<AppInfo>()
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        for (packageInfo in packages) {
            if (packageInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                try {
                    val appName = packageManager.getApplicationLabel(packageInfo).toString()
                    val icon = packageManager.getApplicationIcon(packageInfo.packageName)
                    apps.add(
                        AppInfo(
                            packageName = packageInfo.packageName,
                            appName = appName,
                            icon = icon
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        apps.sortedBy { it.appName }
    }

    fun launchApp(packageName: String) {
        try {
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}