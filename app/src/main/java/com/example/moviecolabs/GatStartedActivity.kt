package com.example.moviecolabs

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.moviecolabs.databinding.ActivityGatStartedBinding

class GatStartedActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityGatStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGatStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)
//        Log.d(TAG, "Thread Started")

        binding.rlGetStarted.setOnClickListener {
            if (sharedPreferences.getString("username", "") == "" && sharedPreferences.getString("password", "") == "") {
                val intentLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentLogin)
            } else {
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}