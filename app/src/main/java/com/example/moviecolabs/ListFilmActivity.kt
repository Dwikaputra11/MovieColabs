package com.example.moviecolabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecolabs.Util.MenuQuery
import com.example.moviecolabs.adapter.AdapterFilm
import com.example.moviecolabs.data.Film
import com.example.moviecolabs.databinding.ActivityListFilmBinding
import com.example.moviecolabs.model.ResponseFilm
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
        filmVM = ViewModelProvider(this)[ViewModelFilm::class.java]
        filmVM.getCallApiFilm()
        adapterFilm = AdapterFilm()
        filmVM.getLiveDataFilm().observe(this, Observer {
            if(it != null){
                adapterFilm.setFilmList(it)
            }
        })

        binding.rvFilm.adapter = adapterFilm
        binding.rvFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapterFilm.setOnItemClickListener(object : AdapterFilm.OnItemClickListener{
            override fun onItemClick(menu: MenuQuery, film: ResponseFilmItem) {
                if(menu == MenuQuery.UPDATE){
                    val mFilm = Film(film.name, film.image, film.director, film.description)
                    val intent = Intent(this@ListFilmActivity, UpdateFilmActivity::class.java)
                    intent.putExtra("film", mFilm)
                    intent.putExtra("id", film.id)
                    startActivity(intent)
                }
            }
        })
    }
}