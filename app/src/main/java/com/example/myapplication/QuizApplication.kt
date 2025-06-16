package com.example.myapplication

import android.app.Application
import android.media.MediaPlayer

class QuizApplication : Application() {

    companion object {
        lateinit var mediaPlayer: MediaPlayer
    }

    override fun onCreate() {
        super.onCreate()
        // inicializa o player em loop assim que o app nascer
        mediaPlayer = MediaPlayer.create(this, R.raw.theme)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onTerminate() {
        // libera o player quando o processo da app for encerrado
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onTerminate()
    }
}
