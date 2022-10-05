package com.example.moviecolabs.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecolabs.data.Film
import com.example.moviecolabs.model.ResponseFilmItem
import com.example.moviecolabs.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    private var liveDataFilm : MutableLiveData<List<ResponseFilmItem>> = MutableLiveData()
    private var updateFilm : MutableLiveData<ResponseFilmItem> = MutableLiveData()
    private var deleteFilm: MutableLiveData<ResponseFilmItem> = MutableLiveData()

    fun getLiveDataFilm() : MutableLiveData<List<ResponseFilmItem>>{
        return liveDataFilm
    }
    fun getCallApiFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseFilmItem>>,
                    response: Response<List<ResponseFilmItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }
                override fun onFailure(call: Call<List<ResponseFilmItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }

    fun getUpdateFilm():MutableLiveData<ResponseFilmItem>{
        return updateFilm
    }

    fun updateCallApiFilm(id: String,film: Film){
        ApiClient.instance.updateFilm(id, film)
            .enqueue(object : Callback<ResponseFilmItem>{
                override fun onResponse(
                    call: Call<ResponseFilmItem>,
                    response: Response<ResponseFilmItem>
                ) {
                    if(response.isSuccessful){
                        Log.d("TAG", "onResponse: Berhasil")
                        Log.d("TAG", "onResponse: ${response.body()}")
                        updateFilm.postValue(response.body())
                    }else{
                        Log.d("TAG", "onResponse: Gagal")
                        updateFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseFilmItem>, t: Throwable) {
                    Log.d("TAG", "onResponse: Gagal Fail")
                    updateFilm.postValue(null)
                }

            })
    }

    fun getDeleteFilm(): MutableLiveData<ResponseFilmItem> = deleteFilm

    fun deleteCallApiFilm(id: String){
        ApiClient.instance.deleteFilm(id)
            .enqueue(object : Callback<ResponseFilmItem>{
                override fun onResponse(
                    call: Call<ResponseFilmItem>,
                    response: Response<ResponseFilmItem>
                ) {
                    if(response.isSuccessful){
                        deleteFilm.postValue(response.body())
                        liveDataFilm.value = liveDataFilm.value?.toMutableList()?.apply {
                            remove(response.body())
                        }?.toList()
                    }else{
                        deleteFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseFilmItem>, t: Throwable) {
                    deleteFilm.postValue(null)
                }

            })
    }

}