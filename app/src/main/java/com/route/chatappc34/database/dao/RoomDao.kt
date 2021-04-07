package com.route.chatappc34.database.dao;

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.route.chatappc34.database.DataBase
import com.route.chatappc34.database.model.Room

public class RoomDao {
    companion object{
        fun insertRoom(room:Room,onCompleteListener: OnCompleteListener<Void>){
            val document = DataBase.getRoomsCollection()
                .document();
            room.id=document.id;
            document.set(room)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getRoomsList(onSuccessListener: OnSuccessListener<QuerySnapshot>,
        onFailureListener: OnFailureListener){
            DataBase.getRoomsCollection()
                .get()
                .addOnSuccessListener (onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
        fun searchInRooms(name:String,onSuccessListener: OnSuccessListener<QuerySnapshot>,
                          onFailureListener: OnFailureListener){
            DataBase.getRoomsCollection()
                .whereEqualTo("name",name)
                .get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)

        }
    }
}
