package com.example.laba2.Network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("study/new_text.json")
    suspend fun getCardInfo(): Response<ResponseBody>
}
