package com.avp.androidcore.looper_handler_handlerthread

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class SimpleWorker(TAG: String = "SimpleWorker") : Thread(TAG) {
    init {
        start()
    }

    /**
     * AtomicBoolean:
     * A boolean value that may be updated atomically.
     * https://stackoverflow.com/questions/4501223/when-do-i-need-to-use-atomicboolean-in-java
     * */
    private val mAlive = AtomicBoolean(true)


    private val mTaskQueue = ConcurrentLinkedQueue<Runnable>()

    override fun run() {
        while (mAlive.get()) {
            val task = mTaskQueue.poll()
            task?.run()
        }
    }

    fun execute(task: Runnable): SimpleWorker {
        mTaskQueue.add(task)
        return this
    }

    fun quit(){
        mAlive.set(false)
    }
}