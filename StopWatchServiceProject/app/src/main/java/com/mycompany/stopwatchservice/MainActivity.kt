package com.mycompany.stopwatchservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.stopwatchservice.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var stopwatchService: StopwatchService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? StopwatchService.LocalBinder
            stopwatchService = binder?.getService()
            isBound = true
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            stopwatchService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To enable viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This binds the local service
        Intent(this, StopwatchService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }

        // Button clicks
        binding.startButton.setOnClickListener {
            stopwatchService?.startStopwatch()
        }
        binding.pauseButton.setOnClickListener {
            stopwatchService?.pauseStopwatch()
        }
        binding.resetButton.setOnClickListener {
            stopwatchService?.resetStopwatch()
        }

        // To start updating every second
        updateElapsedTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    private fun updateElapsedTime() {
        binding.timeTextView.postDelayed({
            if (isBound && stopwatchService != null) {
                val elapsed = stopwatchService?.getElapsedTime() ?: 0L
                val seconds = (elapsed / 1000) % 60
                val minutes = (elapsed / 60000) % 60
                val hours = (elapsed / 3600000) % 24

                // Use an explicit Locale
                val timeString = String.format(
                    Locale.getDefault(),
                    "%02d:%02d:%02d",
                    hours, minutes, seconds
                )
                binding.timeTextView.text = timeString
            }
            // Repeat in 1 second
            updateElapsedTime()
        }, 1000)
    }
}
