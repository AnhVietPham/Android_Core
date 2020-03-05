package com.avp.androidcore.picture_activity

import android.graphics.BitmapFactory
import android.os.Handler
import java.io.InputStream
import java.net.URL

class ImageFetcher(
    private val imageUrl: String,
    private val handler: Handler
) : Runnable {
    override fun run() {
        handler.obtainMessage(PictureActivity.MSG_SHOW_PROGRESS).sendToTarget()
        val inputStream: InputStream?
        val url = URL(imageUrl)
        val conn = url.openConnection()
        conn.connect()
        inputStream = conn.inputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)
        handler.obtainMessage(PictureActivity.MSG_SHOW_IMAGE, bitmap).sendToTarget()
    }
}