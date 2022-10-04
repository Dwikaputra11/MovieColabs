package com.example.moviecolabs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecolabs.model.ResponseFilmItem
import com.example.moviecolabs.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    private var getDataFilm : MutableLiveData<List<ResponseFilmItem>> = MutableLiveData()

    fun getLiveDataFilm() : MutableLiveData<List<ResponseFilmItem>>{
        return getDataFilm
    }
    fun getCallApiFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseFilmItem>>,
                    response: Response<List<ResponseFilmItem>>
                ) {
                    if (response.isSuccessful){
                        getDataFilm.postValue(response.body())
                    }else{
                        getDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseFilmItem>>, t: Throwable) {
                    getDataFilm.postValue(null)
                }

            })
    }
}