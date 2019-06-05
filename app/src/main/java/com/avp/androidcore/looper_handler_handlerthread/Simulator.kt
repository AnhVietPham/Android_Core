package com.avp.androidcore.looper_handler_handlerthread

import kotlin.random.Random


class Simulator(
    private val postOffice: PostOffice,
    private val callback: Client.ClientCallback
) : Runnable {
    private var mRandom: Random = Random(System.currentTimeMillis())
    private var mThread: Thread = Thread(this)
    private var controller: Boolean = true


    fun createClients(num: Int): Simulator {
        for (i in 0 until num) {
            try {
                postOffice.register(Client("BOT$i", callback))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return this
    }

    fun start() {
        if (!mThread.isAlive) {
            mThread.start()
        }
    }

    override fun run() {
        controller = true
        while (controller) {

            val client1Id = mRandom.nextInt(Client.mClientMap.size)
            var client2Id = mRandom.nextInt(Client.mClientMap.size)
            while (client1Id == client2Id) {
                client2Id = mRandom.nextInt(Client.mClientMap.size)
            }

            try {
                postOffice.sendPost(Post(client1Id, client2Id, getRandomMessage()))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

    private fun getRandomMessage(): String {
        return when (mRandom.nextInt(10)) {
            0 -> "Happy Christmas!"
            1 -> "How are you buddy?"
            2 -> "I am so proud of you!"
            3 -> "It's holiday hahaha!"
            4 -> ":P"
            5 -> "LOL!"
            6 -> "Wow!"
            7 -> "Bugger off!"
            8 -> "I love you!"
            9 -> "Go to hell :>"
            else -> "Hmm"
        }
    }

    fun stop() {
        controller = false
        Client.disposeAll()
    }
}