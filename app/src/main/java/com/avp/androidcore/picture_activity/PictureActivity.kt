package com.avp.androidcore.picture_activity

import android.os.Handler
import android.support.v7.app.AppCompatActivity

class PictureActivity : AppCompatActivity() {
    companion object{
        const val IMAGE_URL = "https://www.android.com/static/img/android.png"
        const val MSG_SHOW_PROGRESS = 1
        const val MSG_SHOW_IMAGE = 2
    }
    private var mHandler : Handler? = null
}