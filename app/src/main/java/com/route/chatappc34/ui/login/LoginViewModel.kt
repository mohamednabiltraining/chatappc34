package com.route.chatappc34.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc34.Data
import com.route.chatappc34.base.BaseViewModel
import com.route.chatappc34.database.dao.UsersDao
import com.route.chatappc34.database.model.User

class LoginViewModel :BaseViewModel<Navigator>(){

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<Boolean>(false)
    val passwordError = ObservableField<Boolean>(false)
    val firebaseAuth = Firebase.auth
    fun login(){
       // 1- validate input
       // 2- show or hide errors if there
        if(!valid())return
        showLoading.value=true
        firebaseAuth.signInWithEmailAndPassword(email.get(),password.get())
            .addOnCompleteListener({task->
                if(task.isSuccessful){
                   // retrieve User from db
                    // redirect to home
                    getUserData(firebaseAuth.currentUser.uid)
                }else {
                    showLoading.value=false
                    messageLiveData.value = task.exception?.localizedMessage
                }

            })
    }
    fun getUserData(userId:String){
        UsersDao.getUserData(userId, OnCompleteListener {
            task->
            showLoading.value=false
            if(task.isSuccessful){
                val user = task.result?.toObject(User::class.java)// user retrevied
//                Log.e("user",user?.email?:"")
                Data.user=user
                navigator?.gotoHome()
            }else {
                messageLiveData.value=task.exception?.localizedMessage
            }

//            firebaseAuth.confirmPasswordReset()
        })
    }
    fun valid():Boolean{
        var valid = true
        if(email.get().isNullOrBlank()){
            // show email error
            emailError.set(true)
            valid=false
        }else {
            // hide email error
            emailError.set(false)
        }
        if(password.get().isNullOrBlank() ||password.get()?.length?:0 <6 ){
            // show password error
            passwordError.set(true)
            valid=false
        }else {
            // hide password error
            passwordError.set(false)
        }
        return valid
    }
    fun gotoRegister(){
        navigator?.gotoRegister()
    }
}