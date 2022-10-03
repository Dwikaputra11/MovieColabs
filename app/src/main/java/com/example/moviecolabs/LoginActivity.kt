package com.example.moviecolabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecolabs.databinding.ActivityGatStartedBinding
import com.example.moviecolabs.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            val intentLogin = Intent(this, GatStartedActivity::class.java)
            startActivity(intentLogin)
        }

        binding.tvSignUp.setOnClickListener {
            val intentLogin = Intent(this, RegisterActivity::class.java)
            startActivity(intentLogin)
        }
    }
}