package com.avp.androidcore.picture_activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.avp.androidcore.R
import kotlinx.android.synthetic.main.activity_picture.*

class PictureActivity : AppCompatActivity() {
    companion object {
        const val IMAGE_URL = "https://www.android.com/static/img/android.png"
        const val MSG_SHOW_PROGRESS = 1
        const val MSG_SHOW_IMAGE = 2
    }

    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        mHandler = UIHandler(progressIndicator = progressIndicator, imageView = imv)
        val workerThread = Thread(ImageFetcher(imageUrl = IMAGE_URL, handler = mHandler!!))
        workerThread.start()
    }
}