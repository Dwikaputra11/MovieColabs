package com.example.moviecolabs

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = this.getSharedPreferences("datauser", MODE_PRIVATE)
        val username = sharedPref.getString("username","")
        binding.tvUsername.text = username
        binding.imgUser.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        showFilm()
        binding.btntambah.setOnClickListener {
            startActivity(Intent(this, AddFilmActicity::class.java))
        }
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
        binding.rvFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapterFilm.setOnItemClickListener(object : AdapterFilm.OnItemClickListener{
            override fun onItemClick(menu: MenuQuery, film: ResponseFilmItem) {
                if(menu == MenuQuery.UPDATE){
                    val mFilm = Film(film.name, film.image, film.director, film.description)
                    val intent = Intent(this@ListFilmActivity, UpdateFilmActivity::class.java)
                    intent.putExtra("film", mFilm)
                    intent.putExtra("id", film.id)
                    startActivity(intent)
                }else{
                    if(!isFinishing){
                        showAlertDialog(film.id)
                    }
                }
            }
        })
    }

    fun showAlertDialog(id:String){
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            run {
                filmVM.deleteCallApiFilm(id)
                filmVM.getDeleteFilm().observe(this){
                    if(it != null){
                        Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete Film")
        builder.setMessage("Are you sure to delete this film?")
        builder.create().show()
    }
}