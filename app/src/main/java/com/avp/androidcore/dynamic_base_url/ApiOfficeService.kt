package com.avp.androidcore.dynamic_base_url

import retrofit2.Call
import retrofit2.http.GET

interface ApiOfficeService {


    @GET("login/sendoid")
    fun getUserInfo() : Call<String>
}