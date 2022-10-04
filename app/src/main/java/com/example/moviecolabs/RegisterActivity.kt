package com.example.moviecolabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.moviecolabs.databinding.ActivityRegisterBinding
import com.example.moviecolabs.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

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
            val name = binding.etUsername.text.toString()
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val repeatPassword = binding.etConfirmPassword.text.toString()

            if (password == repeatPassword) {
                addUser(name,username,password)
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Akun Berhasil Terdaftar", Toast.LENGTH_SHORT).show()
            } else if (password != repeatPassword) {
                Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun addUser(name: String, username: String, password: String) {
        val viewModel = ViewModelProvider(this)[ViewModelUser::class.java]
        viewModel.callPostUser(name,username, password)
        viewModel.postLiveDataUser().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Akun Berhasil Terdaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}