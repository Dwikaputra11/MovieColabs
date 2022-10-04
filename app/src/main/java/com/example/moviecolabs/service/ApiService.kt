package com.example.moviecolabs.service

import com.example.moviecolabs.model.getResponFilmItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("film")
    fun getAllFilm(): Call<List<getResponFilmItem>>
}