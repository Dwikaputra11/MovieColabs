package com.example.moviecolabs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.moviecolabs.databinding.ActivityGatStartedBinding
import com.example.moviecolabs.databinding.ActivityLoginBinding
import com.example.moviecolabs.databinding.ActivityRegisterBinding
import com.example.moviecolabs.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }

        binding.tvLogin.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }

        binding.btnRegister.setOnClickListener {
            var name = binding.etUsername.text.toString()
            var username = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            var repeatPassword = binding.etConfirmPassword.text.toString()

            if (password == repeatPassword) {
                addUser(name,username,password)
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Akun Berhasil Terdaftar", Toast.LENGTH_SHORT).show()
            } else if (password != repeatPassword) {
                Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun addUser(name: String, username: String, password: String) {
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.callPostUser(name,username, password)
        viewModel.postLiveDataUser().observe(this, {
            if (it != null) {
                Toast.makeText(this, "Akun Berhasil Terdaftar", Toast.LENGTH_SHORT).show()
            }
        })
    }
}