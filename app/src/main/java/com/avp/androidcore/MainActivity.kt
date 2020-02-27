package com.avp.androidcore

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.avp.androidcore.looper_handler_handlerthread.SimpleWorker
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mSimpleWorker: SimpleWorker? = null
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSimpleWorker = SimpleWorker()
        mSimpleWorker
            ?.execute(Runnable {
                Thread.sleep(1000)
                val message = Message.obtain()
                message.obj = "Task 1 Completed!"
                mHandler?.sendMessage(message)
                Log.e("COUNT", "Task 1 Completed!")
            })
            ?.execute(Runnable {
                Thread.sleep(500)
                val message = Message.obtain()
                message.obj = "Task 2 Completed!"
                mHandler?.sendMessage(message)
                Log.e("COUNT", "Task 2 Completed!")
            })
            ?.execute(Runnable {
                Thread.sleep(2000)
                val message = Message.obtain()
                message.obj = "Task 3 Completed!"
                mHandler?.sendMessage(message)
                Log.e("COUNT", "Task 3 Completed!")
            })
            ?.execute(Runnable {
                Thread.sleep(400)
                val message = Message.obtain()
                message.obj = "Task 4 Completed!"
                mHandler?.sendMessage(message)
                Log.e("COUNT", "Task 4 Completed!")
            })
            ?.execute(Runnable {
                Thread.sleep(5000)
                val message = Message.obtain()
                message.obj = "Task 5 Completed!"
                mHandler?.sendMessage(message)
                Log.e("COUNT", "Task 4 Completed!")
            })
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                tvMessage.text = msg?.obj.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSimpleWorker?.quit()
    }
}