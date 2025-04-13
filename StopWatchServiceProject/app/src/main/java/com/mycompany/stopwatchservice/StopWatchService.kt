package com.mycompany.stopwatchservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.SystemClock

class StopwatchService : Service() {

    private val binder = LocalBinder()

    // This tracks time the stopwatch started (in elapsedRealtime)
    private var startTime = 0L
    // Whether the stopwatch is currently running
    private var running = false
    // The accumulated time in ms (so we can pause/resume)
    private var elapsedTime = 0L

    // The handler used to update elapsedTime
    private val handler = Handler(Looper.getMainLooper())

    // A runnable that updates 'elapsedTime' every 100ms if 'running' is true
    private val updateRunnable = object : Runnable {
        override fun run() {
            if (running) {
                val now = SystemClock.elapsedRealtime()
                elapsedTime = now - startTime
                // Re-post after 100ms
                handler.postDelayed(this, 100)
            }
        }
    }

    // For local binding
    inner class LocalBinder : Binder() {
        fun getService(): StopwatchService = this@StopwatchService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun startStopwatch() {
        if (!running) {
            // If paused, you offset the startTime so you resume
            startTime = SystemClock.elapsedRealtime() - elapsedTime
            running = true
            handler.post(updateRunnable)
        }
    }

    fun pauseStopwatch() {
        running = false
    }

    fun resetStopwatch() {
        running = false
        elapsedTime = 0L
    }

    fun getElapsedTime(): Long {
        return elapsedTime
    }
}
