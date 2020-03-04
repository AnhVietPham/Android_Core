package com.avp.androidcore.dynamic_base_url

import retrofit2.Call
import retrofit2.http.GET

interface ApiHomeService {


    @GET("v1/wallets")
    fun getInfo() : Call<String>
}