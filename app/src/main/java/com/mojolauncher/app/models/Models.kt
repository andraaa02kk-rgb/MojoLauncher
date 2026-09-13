package com.mojolauncher.app.models

data class AppInfo(
    val packageName: String,
    val appName: String,
    val icon: android.graphics.drawable.Drawable?
)

data class LauncherConfig(
    val enableVideoBackground: Boolean = true,
    val enablePhotoBackground: Boolean = true,
    val enableSoundEffects: Boolean = true,
    val videoBackgroundPath: String = "",
    val photoBackgroundPath: String = ""
)