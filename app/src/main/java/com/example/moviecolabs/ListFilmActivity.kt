package com.example.moviecolabs

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecolabs.adapter.adapterFilm
import com.example.moviecolabs.databinding.ActivityListFilmBinding
import com.example.moviecolabs.model.getResponFilmItem
import com.example.moviecolabs.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityListFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityListFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tampilFilm()

    }

    fun tampilFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<getResponFilmItem>>{
                override fun onResponse(
                    call: Call<List<getResponFilmItem>>,
                    response: Response<List<getResponFilmItem>>
                ) {
                    if (response.isSuccessful){
                        binding.rvFilm.layoutManager = LinearLayoutManager(this@ListFilmActivity, LinearLayoutManager.VERTICAL,false)
                        binding.rvFilm.adapter = adapterFilm(response.body()!!)
                    }else{
                        Toast.makeText(this@ListFilmActivity, "Data Kosong", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<List<getResponFilmItem>>, t: Throwable) {
                    Toast.makeText(this@ListFilmActivity, "Terjadi Kesalahan Data", Toast.LENGTH_SHORT).show()
                }

            })
    }
}