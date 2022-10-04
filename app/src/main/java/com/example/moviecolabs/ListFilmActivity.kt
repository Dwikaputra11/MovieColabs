package com.example.moviecolabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecolabs.adapter.AdapterFilm
import com.example.moviecolabs.databinding.ActivityListFilmBinding
import com.example.moviecolabs.model.ResponseFilmItem
import com.example.moviecolabs.service.ApiClient
import com.example.moviecolabs.viewmodel.ViewModelFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityListFilmBinding
    private lateinit var filmVM: ViewModelFilm
    private lateinit var adapterFilm: AdapterFilm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityListFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFilm()

    }

    private fun showFilm(){
        filmVM =ViewModelProvider(this)[ViewModelFilm::class.java]
        filmVM.getCallApiFilm()
        adapterFilm = AdapterFilm()
        filmVM.getLiveDataFilm().observe(this, Observer {
            if(it != null){
                adapterFilm.setFilmList(it)
            }
        })

        binding.rvFilm.adapter = adapterFilm
        binding.rvFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

//    private fun showFilm(){
//        ApiClient.instance.getAllFilm()
//            .enqueue(object : Callback<List<ResponseFilmItem>>{
//                override fun onResponse(
//                    call: Call<List<ResponseFilmItem>>,
//                    response: Response<List<ResponseFilmItem>>
//                ) {
//                    if (response.isSuccessful){
//
//                    }else{
//                        Toast.makeText(this@ListFilmActivity, "Data Kosong", Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<List<ResponseFilmItem>>, t: Throwable) {
//                    Toast.makeText(this@ListFilmActivity, "Terjadi Kesalahan Data", Toast.LENGTH_SHORT).show()
//                }
//
//            })
//    }

    fun updateFilm(){

    }
}