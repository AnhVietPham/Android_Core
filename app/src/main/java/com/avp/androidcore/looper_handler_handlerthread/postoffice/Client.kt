package com.avp.androidcore.looper_handler_handlerthread.postoffice

class Client(val name: String, private val callback: ClientCallback) {

    var mId: Int = 0

    companion object {
        var mCounter: Int = 0
        var mClientMap: LinkedHashMap<Int, Client> = linkedMapOf()
        fun disposeAll() {
            mClientMap.clear()
        }
    }

    init {
        mId = mCounter++
        mClientMap[mId] = this
    }

    fun onPostReceived(post: Post) {
        callback.onNewPost(
            mClientMap[post.receiverId]!!,
            mClientMap[post.senderId]!!,
            post.message
        )
    }

    fun dispose(){
        mClientMap.remove(mId)
    }

    interface ClientCallback {
        fun onNewPost(receiver: Client, sender: Client, message: String)
    }
}