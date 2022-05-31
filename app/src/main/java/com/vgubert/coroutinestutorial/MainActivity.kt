package com.vgubert.coroutinestutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vgubert.coroutinestutorial.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val job = GlobalScope.launch (Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculation")
            withTimeout(3000L) {
                for(i in 30..40) {
                    if(isActive) {
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }
            Log.d(TAG, "Ending long running calculation")
        }
        runBlocking {
            delay(2000L)
            job.cancel()
            Log.d(TAG, "Canceled job...")
        }
    }

    fun fib(n: Int): Long {
        return if(n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }

}