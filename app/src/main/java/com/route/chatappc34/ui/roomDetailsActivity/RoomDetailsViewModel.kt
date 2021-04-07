package com.route.chatappc34.ui.roomDetailsActivity

import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Timestamp
import com.route.chatappc34.Data
import com.route.chatappc34.base.BaseViewModel
import com.route.chatappc34.database.dao.MessagesDao
import com.route.chatappc34.database.model.Message

class RoomDetailsViewModel :BaseViewModel<Navigator>(){
    val messageField=ObservableField<String>()
    var roomId :String?=null
    fun sendMessage(){
        if(!validMessage())
            return
        // send message to room in fire store
        val messageObj =Message(messageText = messageField.get(),
            senderId = Data.user?.id,
            senderName =Data.user?.userName ,
            roomId =roomId ,
            time = Timestamp.now()
            )
        MessagesDao.sendMessage(messageObj, OnSuccessListener {
            messageField.set("")
        }, OnFailureListener{
                messageLiveData.value = it.localizedMessage
        })

    }

    private fun validMessage(): Boolean {
        if(messageField.get().isNullOrBlank()){
            return false
        }
        return true;
    }
}