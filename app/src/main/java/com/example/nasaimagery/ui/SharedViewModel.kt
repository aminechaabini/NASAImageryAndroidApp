package com.example.nasaimagery.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaimagery.data.network.Result
import com.example.nasaimagery.data.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel : ViewModel(){
    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result> = _result
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate
    fun getResult(api_key: String, date: String) {
        RetrofitHelper.retrofitService.getResult(api_key, date).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    _result.value = response.body()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }
        }
        )
    }
    fun getResultTodaysDate(api_key: String) {
        RetrofitHelper.retrofitService.getResultTodaysDate(api_key).enqueue(object :
            Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    _result.value = response.body()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }
        }
        )
    }
    public fun setDate(date: String) {
        _selectedDate.value = date
    }
//    fun getSelectedDate(date: String){
//        setDate(date)
//    }
}