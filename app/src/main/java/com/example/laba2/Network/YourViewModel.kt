package com.example.laba2.Network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laba2.Data.CardItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YourViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://develtop.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    val cards: MutableLiveData<List<CardItem>?> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = apiService.getCardInfo()
                if (response.isSuccessful) {
                    val cardList = response.body()?.let { CardItem.fromJson(it.string()) }
                    cards.value = cardList
                } else {
                    error.value = "Failed to load data"
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
}

