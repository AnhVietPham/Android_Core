package com.avp.androidcore.looper_handler_handlerthread.postoffice

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import java.lang.ref.WeakReference

class PostOffice(
    name: String = PostOffice::class.java.simpleName,
    private val mClientDetailsMap: LinkedHashMap<Int, Handler>
) : HandlerThread(name) {
    fun register(client: Client?) {
        if (client == null) {
            throw InvalidRequestException(
                "The Client can't be null"
            )
        }
        if (mClientDetailsMap.containsKey(client.mId)) {
            throw AlreadyExistsException(
                "The Client is already registered with this Id"
            )
        }
        val clientWeakReference: WeakReference<Client> = WeakReference(client)
        val handler = object : Handler(looper) {
            override fun handleMessage(msg: Message?) {
                val clientA = clientWeakReference.get()
                if (clientA != null) {
                    if (msg?.obj is String) {
                        clientA.onPostReceived(
                            Post(
                                msg.arg1,
                                msg.arg2,
                                msg.obj.toString()
                            )
                        )
                    } else {
                        msg?.let {
                            clientA.onPostReceived(
                                Post(
                                    msg.arg1,
                                    msg.arg2,
                                    "No body present"
                                )
                            )
                        }
                    }
                }
            }
        }
        mClientDetailsMap[client.mId] = handler
    }

    fun sendPost(post: Post?){
        if (post == null){
            throw InvalidRequestException(
                "Post can't be null"
            )
        }
        if (!mClientDetailsMap.containsKey(post.receiverId)){
            throw NotRegisteredException(
                "Post receiver is not register"
            )
        }
        val handler = mClientDetailsMap[post.receiverId]
        val message = Message()
        message.arg1 = post.senderId
        message.arg2 = post.receiverId
        message.obj = post.message
        handler?.sendMessage(message)
    }

    companion object {

        class InvalidRequestException(message: String) : Exception(message)

        class AlreadyExistsException(message: String) : Exception(message)

        class NotRegisteredException(message: String) : Exception(message)

    }
}