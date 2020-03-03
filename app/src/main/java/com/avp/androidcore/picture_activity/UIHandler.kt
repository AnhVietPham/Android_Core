package com.avp.androidcore.picture_activity

import android.os.Handler
import android.os.Message

class UIHandler : Handler() {
    override fun handleMessage(msg: Message?) {
        when (msg?.what) {
            PictureActivity.MSG_SHOW_IMAGE -> {

            }
            PictureActivity.MSG_SHOW_PROGRESS -> {

            }
        }
    }
}