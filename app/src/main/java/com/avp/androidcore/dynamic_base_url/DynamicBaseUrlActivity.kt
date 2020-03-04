package com.avp.androidcore.dynamic_base_url

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.avp.androidcore.R
import kotlinx.android.synthetic.main.activity_dynamic_base_url.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DynamicBaseUrlActivity : AppCompatActivity(){
    private val apiOfficeUrl = "http://xxxabc.com/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_base_url)
        btnCallApiHome.setOnClickListener {
            val httpGenerator = HttpGenerator.getHttpGenerator().create(ApiHomeService::class.java)
            val callApiHome = httpGenerator.getInfo()
            callApiHome.enqueue(object : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("DynamicBaseUrlActivity", call.request().url.toString())
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("DynamicBaseUrlActivity", call.request().url.toString())
                }

            })
        }

        btnCallApiOffice.setOnClickListener {
            val httpGenerator = HttpGenerator.getHttpGenerator(apiBaseUrl = apiOfficeUrl).create(ApiOfficeService::class.java)
            val callApiOffice = httpGenerator.getUserInfo()
            callApiOffice.enqueue(object : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("DynamicBaseUrlActivity", call.request().url.toString())
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("DynamicBaseUrlActivity", call.request().url.toString())
                }

            })
        }
    }
}