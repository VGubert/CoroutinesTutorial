package com.vgubert.coroutinestutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.vgubert.coroutinestutorial.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartActivity.setOnClickListener{
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "Still running..")
                }
            }
            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}