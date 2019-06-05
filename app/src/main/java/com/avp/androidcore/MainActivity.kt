package com.avp.androidcore

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.avp.androidcore.looper_handler_handlerthread.Client
import com.avp.androidcore.looper_handler_handlerthread.PostOffice
import com.avp.androidcore.looper_handler_handlerthread.Simulator
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), Client.ClientCallback {
    private var mSimulator: Simulator? = null
    private var mPostOffice: PostOffice? = null
    private var mPostListAdapter: PostListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postFeedRecyclerView.layoutManager = LinearLayoutManager(this)
        mPostListAdapter = PostListAdapter(this, LinkedList())
        postFeedRecyclerView.adapter = mPostListAdapter
    }

    override fun onDestroy() {
        mSimulator?.stop()
        mPostOffice?.quit()
        super.onDestroy()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mPostOffice = PostOffice("", LinkedHashMap())
        mPostOffice!!.start()
        mSimulator = Simulator(mPostOffice!!, this)
        mSimulator!!.createClients(10).start()
    }

    override fun onNewPost(receiver: Client, sender: Client, message: String) {
        runOnUiThread {
            val position = mPostListAdapter?.feedItemList?.size
            mPostListAdapter?.feedItemList?.add(PostListAdapter.FeedItem(sender.name, receiver.name, message))
            position?.let {
                postFeedRecyclerView.adapter?.notifyItemInserted(position)
                postFeedRecyclerView.smoothScrollToPosition(position)
            }
        }
    }

    class PostListAdapter(private val context: Context, val feedItemList: LinkedList<FeedItem>) :
        RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

        override fun getItemCount(): Int {
            return feedItemList.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_list_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.onBind(position)
        }

        class FeedItem(var senderName: String, var receiverName: String, var message: String)

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private var senderNameTxt: TextView = itemView.findViewById(R.id.senderNameTxt)
            private var receiverNameTxt: TextView = itemView.findViewById(R.id.receiverNameTxt)
            private var msgTxt: TextView = itemView.findViewById(R.id.msgTxt)

            fun onBind(position: Int) {
                val feedItem = feedItemList[position]
                senderNameTxt.text = feedItem.senderName
                receiverNameTxt.text = feedItem.receiverName
                msgTxt.text = feedItem.message
            }
        }
    }
}
