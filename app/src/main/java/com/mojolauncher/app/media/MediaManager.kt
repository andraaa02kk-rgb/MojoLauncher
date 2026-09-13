package com.mojolauncher.app.media

import android.content.Context
import android.media.MediaPlayer
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class MediaManager(context: Context) {
    private var exoPlayer: ExoPlayer? = null
    private var mediaPlayer: MediaPlayer? = null
    private val appContext = context.applicationContext

    init {
        exoPlayer = ExoPlayer.Builder(appContext).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_OFF
        }
    }

    fun playVideo(videoUri: String) {
        try {
            val mediaItem = MediaItem.fromUri(videoUri)
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
            exoPlayer?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopVideo() {
        exoPlayer?.stop()
    }

    fun pauseVideo() {
        exoPlayer?.pause()
    }

    fun resumeVideo() {
        exoPlayer?.play()
    }

    fun playSound(soundResId: Int) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(appContext, soundResId)
            mediaPlayer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopSound() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun getExoPlayer(): ExoPlayer? = exoPlayer

    fun release() {
        exoPlayer?.release()
        mediaPlayer?.release()
        exoPlayer = null
        mediaPlayer = null
    }
}