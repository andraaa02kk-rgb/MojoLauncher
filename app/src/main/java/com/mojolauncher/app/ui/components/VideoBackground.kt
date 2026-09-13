package com.mojolauncher.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoBackground(
    exoPlayer: ExoPlayer?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (exoPlayer != null) {
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        useController = false
                        setShutterBackgroundColor(android.graphics.Color.BLACK)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}