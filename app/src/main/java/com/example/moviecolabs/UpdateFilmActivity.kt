package com.example.moviecolabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviecolabs.data.Film
import com.example.moviecolabs.databinding.ActivityUpdateFilmBinding
import com.example.moviecolabs.viewmodel.ViewModelFilm

class UpdateFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateFilmBinding
    private var idFilm = ""
    private lateinit var film: Film
    private lateinit var filmVM: ViewModelFilm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        film  = intent.getParcelableExtra<Film>("film")!!
        idFilm = intent.getStringExtra("id").toString()
        binding.etName.setText(film.name)
        binding.etDirector.setText(film.director)
        binding.etDesc.setText(film.description)

        binding.btnUpdate.setOnClickListener {
            updateFilm()
            startActivity(Intent(this, ListFilmActivity::class.java))
        }
    }

    private fun updateFilm(){
        filmVM = ViewModelProvider(this)[ViewModelFilm::class.java]
        val name = binding.etName.text.toString()
        val director = binding.etDirector.text.toString()
        val desc = binding.etDesc.text.toString()
        val filmUpdate = Film(name, film.image,director, desc)

        filmVM.updateCallApiFilm(idFilm, filmUpdate)
        filmVM.getUpdateFilm().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Update Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}