package com.example.moviecolabs.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecolabs.model.getResponFilmItem
import com.example.moviecolabs.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var getDataFilm : MutableLiveData<List<getResponFilmItem>>

    init {
        getDataFilm = MutableLiveData()
    }
    fun getLiveDatafilm() : MutableLiveData<List<getResponFilmItem>>{
        return getDataFilm
    }
    fun getFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<getResponFilmItem>>{
                override fun onResponse(
                    call: Call<List<getResponFilmItem>>,
                    response: Response<List<getResponFilmItem>>
                ) {
                    if (response.isSuccessful){
                        getDataFilm.postValue(response.body())
                    }else{
                        getDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<getResponFilmItem>>, t: Throwable) {
                    getDataFilm.postValue(null)
                }

            })
    }
}