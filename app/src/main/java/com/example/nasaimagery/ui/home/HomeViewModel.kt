package com.example.nasaimagery.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaimagery.data.network.Result
import com.example.nasaimagery.data.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val result = MutableLiveData<Result>()
    val image: LiveData<Result> = result
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate
    fun getResult(key: String, date: String) {
        RetrofitHelper.retrofitService.getResult(key, date).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }
        }
        )
    }
    fun setDate(date: String) {
        _selectedDate.value = date
    }
}