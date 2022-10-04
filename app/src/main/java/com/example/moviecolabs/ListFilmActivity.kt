package com.example.moviecolabs

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.moviecolabs.adapter.ViewPagerAdapter
import com.example.moviecolabs.adapter.adapterFilm
import com.example.moviecolabs.databinding.ActivityListFilmBinding
import com.example.moviecolabs.model.getResponFilmItem
import com.example.moviecolabs.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityListFilmBinding
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityListFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tampilFilm()
        viewPager()

    }

    fun tampilFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<getResponFilmItem>>{
                override fun onResponse(
                    call: Call<List<getResponFilmItem>>,
                    response: Response<List<getResponFilmItem>>
                ) {
                    if (response.isSuccessful){
                        binding.rvFilm.layoutManager = LinearLayoutManager(this@ListFilmActivity, LinearLayoutManager.HORIZONTAL,false)
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

    fun viewPager(){


        // initializing variables
        // of below line with their id.
        viewPager = findViewById(R.id.idViewPager)

        // on below line we are initializing
        // our image list and adding data to it.
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.banner1
        imageList = imageList + R.drawable.banner2
        imageList = imageList + R.drawable.banner3
        imageList = imageList + R.drawable.banner5
        imageList = imageList + R.drawable.banner6

        // on below line we are initializing our view
        // pager adapter and adding image list to it.
        viewPagerAdapter = ViewPagerAdapter(this@ListFilmActivity, imageList)

        // on below line we are setting
        // adapter to our view pager.
        viewPager.adapter = viewPagerAdapter
    }
}