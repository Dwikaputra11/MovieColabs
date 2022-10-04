package com.example.moviecolabs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecolabs.databinding.ActivityGetStartedBinding

class GatStartedActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)
//        Log.d(TAG, "Thread Started")

        binding.rlGetStarted.setOnClickListener {
            if (sharedPreferences.getString("username", "") == "" && sharedPreferences.getString("password", "") == "") {
                val intentLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentLogin)
            } else {
                startActivity(Intent(this,ProfileActivity::class.java))
            }
        }
    }
}