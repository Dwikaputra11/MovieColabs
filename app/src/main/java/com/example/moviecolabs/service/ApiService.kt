package com.example.moviecolabs.service

import android.webkit.WebResourceRequest
import com.example.moviecolabs.data.Film
import com.example.moviecolabs.model.ResponseFilmItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("film")
    fun getAllFilm(): Call<List<ResponseFilmItem>>

    @PUT("film/{id}")
    fun updateFilm(@Path("id") id: String, @Body film: Film): Call<ResponseFilmItem>

    @DELETE("film/{id}")
    fun deleteFilm(@Path("id") id: String): Call<ResponseFilmItem>

    @POST("film")
    fun addFilm(@Body request: Film) : Call<ResponseFilmItem>
}