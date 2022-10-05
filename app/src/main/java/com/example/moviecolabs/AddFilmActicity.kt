package com.example.moviecolabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviecolabs.databinding.ActivityAddFilmActicityBinding
import com.example.moviecolabs.viewmodel.ViewModelFilm

class AddFilmActicity : AppCompatActivity() {

    lateinit var binding : ActivityAddFilmActicityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityAddFilmActicityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnadd.setOnClickListener {
            var name = binding.etName.text.toString()
            var img = binding.etImg.text.toString()
            var direc = binding.etDirector.text.toString()
            var desc = binding.etDesc.text.toString()
            addDataFilm(name,img,direc,desc)
        }
    }
    fun addDataFilm(name : String,image : String,director : String, description : String){
        var viewmodel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewmodel.addPostApiFilm(name,image,director,description)
        viewmodel.getAddFilm().observe(this, Observer {
            if (it !=null){
                startActivity(Intent(this, ListFilmActivity::class.java))
                Toast.makeText(this, "insert is success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "insert is filed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}