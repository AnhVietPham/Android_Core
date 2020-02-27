package com.avp.androidcore.looper_handler_handlerthread

import android.os.Handler
import android.os.HandlerThread

class Worker(TAG: String = "Worker") : HandlerThread(TAG) {
    private var mHandler : Handler? = null

    init {
        start()
        mHandler = Handler(looper)
    }

    fun execute(task: Runnable) : Worker{
        mHandler?.post(task)
        return this
    }
}