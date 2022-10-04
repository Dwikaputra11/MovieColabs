package com.example.moviecolabs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecolabs.model.DataUser
import com.example.moviecolabs.model.ResponseUserItem
import com.example.moviecolabs.network.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser: ViewModel() {
    lateinit var postLDUser: MutableLiveData<ResponseUserItem>

    init {
        postLDUser = MutableLiveData()
    }

    fun postLiveDataUser(): MutableLiveData<ResponseUserItem> {
        return postLDUser
    }

    fun callPostUser(name: String, username: String, password: String) {
        RetrofitUser.instance.postUser(DataUser(name,username,password))
            .enqueue(object : Callback<ResponseUserItem> {
                override fun onResponse(
                    call: Call<ResponseUserItem>,
                    response: Response<ResponseUserItem>
                ) {
                    if(response.isSuccessful) {
                        postLDUser.postValue(response.body())
                    } else {
                        postLDUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseUserItem>, t: Throwable) {
                    postLDUser.postValue(null)
                }

            })
    }
}