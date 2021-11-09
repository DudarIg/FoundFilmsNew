package ru.dudar.findfilms.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "KinoFetchr"

class KinopoiskRepo {

    private val kinopoiskApi: KinopoiskApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        kinopoiskApi = retrofit.create(KinopoiskApi::class.java)
    }

    fun fetchContens() : LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val kinopoiskRequest: Call<String> = kinopoiskApi.fetchContents()

        kinopoiskRequest.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Ответ сервера")
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "Ошибка")
            }

        } )
        return responseLiveData
    }
}