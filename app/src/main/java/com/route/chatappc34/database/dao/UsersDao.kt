package com.route.chatappc34.database.dao

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.route.chatappc34.database.DataBase
import com.route.chatappc34.database.model.User

class UsersDao {
    companion object{
        fun addUser(user: User,onCompleteListener: OnCompleteListener<Void>){
            DataBase.getUsersCollection()
                .document(user.id?:"")
                .set(user)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getUserData(userId:String,onCompleteListener: OnCompleteListener<DocumentSnapshot>){
            DataBase.getUsersCollection()
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener)

        }

    }
}