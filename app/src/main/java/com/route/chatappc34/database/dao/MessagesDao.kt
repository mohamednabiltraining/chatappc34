package com.route.chatappc34.database.dao

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.route.chatappc34.database.DataBase
import com.route.chatappc34.database.model.Message

class MessagesDao {
    companion object{
        fun sendMessage(message: Message,onSuccessListener: OnSuccessListener<Void>,onFailureListener: OnFailureListener){
         val room =  DataBase.getRoomsCollection()
             .document(message.roomId?:"");
            val messages = room.collection("messages");
            val newMessageDoc = messages.document();
            message.id = newMessageDoc.id;
            newMessageDoc.set(message)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
        fun getMessagesRef(roomId:String):CollectionReference{
            return DataBase.getRoomsCollection()
                .document(roomId)
                .collection("messages")
        }
    }

}