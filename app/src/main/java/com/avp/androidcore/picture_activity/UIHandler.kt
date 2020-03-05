package com.avp.androidcore.picture_activity

import android.graphics.Bitmap
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar

class UIHandler(private val progressIndicator: ProgressBar,
                private val imageView: ImageView
                ) : Handler() {
    override fun handleMessage(msg: Message?) {
        when (msg?.what) {
            PictureActivity.MSG_SHOW_IMAGE -> {
                progressIndicator.visibility = View.GONE
                imageView.setImageBitmap(msg.obj as? Bitmap)

            }
            PictureActivity.MSG_SHOW_PROGRESS -> {
                progressIndicator.visibility = View.VISIBLE
            }
        }
    }
}