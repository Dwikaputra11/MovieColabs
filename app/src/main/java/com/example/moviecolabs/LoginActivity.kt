package com.example.moviecolabs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.moviecolabs.databinding.ActivityGatStartedBinding
import com.example.moviecolabs.databinding.ActivityLoginBinding
import com.example.moviecolabs.model.DataUser
import com.example.moviecolabs.model.ResponseUserItem
import com.example.moviecolabs.network.RetrofitUser
import com.example.moviecolabs.viewmodel.ViewModelUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityLoginBinding
    lateinit var dataUser: List<ResponseUserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)

        binding.ivBack.setOnClickListener {
            val intentLogin = Intent(this, GatStartedActivity::class.java)
            startActivity(intentLogin)
        }

        binding.tvSignUp.setOnClickListener {
            val intentLogin = Intent(this, RegisterActivity::class.java)
            startActivity(intentLogin)
        }

        binding.btnLogin.setOnClickListener {

            var username = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            var saveUser = sharedPreferences.edit()

            RetrofitUser.instance.getAllUser()
                .enqueue(object : Callback<List<ResponseUserItem>> {
                    override fun onResponse(
                        call: Call<List<ResponseUserItem>>,
                        response: Response<List<ResponseUserItem>>
                    ) {
                        if (response.isSuccessful) {
                            var userlist = response.body()?.filter {
                                it.username == username && it.password == password
                            } as List<ResponseUserItem>
//                            Log.d("TAG", "onResponse: $user")
                            if (!userlist.indices.isEmpty()) {
                                var user = userlist.first {
                                    it.username == username && it.password == password
                                }
                                saveUser.putString("name",user.name)
                                saveUser.putString("username",user.username)
                                saveUser.putString("password",user.password)
                                saveUser.apply()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            } else {
                                Toast.makeText(this@LoginActivity, "Gagal Login", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseUserItem>>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Gagal Login", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

}