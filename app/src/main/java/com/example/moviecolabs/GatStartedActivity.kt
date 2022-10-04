package com.example.moviecolabs

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.moviecolabs.databinding.ActivityGatStartedBinding

class GatStartedActivity : AppCompatActivity() {

    lateinit var binding: ActivityGatStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGatStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Log.d(TAG, "Thread Started")

        binding.rlGetStarted.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}