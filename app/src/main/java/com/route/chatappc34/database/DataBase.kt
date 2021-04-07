package com.route.chatappc34.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object DataBase {
    private val dataBase =FirebaseFirestore.getInstance()
    val USERS_PATH = "users"
    fun getUsersCollection():CollectionReference{
        return dataBase.collection(USERS_PATH);
    }
    val ROOMS_PATH = "rooms"
    fun getRoomsCollection():CollectionReference{
        return dataBase.collection(ROOMS_PATH);
    }

}