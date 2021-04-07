package com.route.chatappc34.ui.roomDetailsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.route.chatappc34.R
import com.route.chatappc34.database.dao.MessagesDao
import com.route.chatappc34.database.model.Message
import com.route.chatappc34.database.model.Room
import com.route.chatappc34.databinding.ActivityRoomDetailsBinding
import com.route.islamigsun.base.BaseActivity

class RoomDetailsActivity : BaseActivity<ActivityRoomDetailsBinding,RoomDetailsViewModel>() {
     var room:Room?=null
    lateinit var messagesAdapter: MessagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        room = intent.getParcelableExtra("room")
        viewModel.roomId=room?.id
        viewDataBinding.vm=viewModel
        initRecyclerView()
        subscribeToRoomMessages(room?.id?:"")
    }

    private fun subscribeToRoomMessages(roomId: String) {
        MessagesDao.getMessagesRef(roomId)
            .orderBy("time",Query.Direction.ASCENDING)
            .addSnapshotListener{snapshots, e ->
                if (e != null) {
                    showDialoge(message = e.localizedMessage)
                    return@addSnapshotListener
                }

                val addedMessages = mutableListOf<Message>()
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED ->{
                           val newMessage =  dc.document.toObject(Message::class.java)
                            addedMessages.add(newMessage)
                        }// Log.d(TAG, "New city: ${dc.document.data}")
                       // DocumentChange.Type.REMOVED -> Log.d(TAG, "Removed city: ${dc.document.data}")
                    }
                }
                messagesAdapter.addMessages(addedMessages)
                viewDataBinding.
                messagesRecyclerView.smoothScrollToPosition(messagesAdapter.messagesList.size-1)
            }
    }

    private fun initRecyclerView() {
        messagesAdapter = MessagesAdapter(mutableListOf<Message>())
       val layoutManager =  LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        layoutManager.stackFromEnd=true
        viewDataBinding.messagesRecyclerView.layoutManager = layoutManager
        viewDataBinding.messagesRecyclerView.adapter = messagesAdapter
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }

    override fun initializeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }
}