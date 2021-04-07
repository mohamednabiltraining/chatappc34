package com.route.chatappc34.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc34.Data
import com.route.chatappc34.base.BaseViewModel
import com.route.chatappc34.database.dao.UsersDao
import com.route.chatappc34.database.model.User

class RegisterViewModel :BaseViewModel<Navigator>() {
    val name = ObservableField<String>()
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val nameError = ObservableField<Boolean>()
    val userNameError = ObservableField<Boolean>()
    val emailError = ObservableField<Boolean>()
    val passwordError = ObservableField<Boolean>()

    val firebaseAuth = Firebase.auth

    fun register(){
        if(!valid())return
        // validation is success
        showLoading.value= true
        firebaseAuth.createUserWithEmailAndPassword(email.get(),password.get())
            .addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                    val firebaseUser = firebaseAuth.currentUser;
                    val user = User(firebaseUser.uid,name.get(),userName.get(),email.get())
                    addUserToDB(user)
                }else {
                    showLoading.value=false
                    messageLiveData.value = task.exception?.localizedMessage
                }

            })
    }
    fun addUserToDB(user: User){
        UsersDao.addUser(user, OnCompleteListener {
            showLoading.value=false
            if(it.isSuccessful){
                // goto homePage
               // messageLiveData.value ="user added in db"
                Data.user=user
                navigator?.gotoHomeActivity()
            }else {
                messageLiveData.value = it.exception?.localizedMessage
            }
        })
    }
    fun valid():Boolean{
        var isValid = true;
        if(name.get().isNullOrBlank()){
            nameError.set(true)
            isValid=false
        }else {
            nameError.set(false)
        }
        if(email.get().isNullOrBlank()){
            emailError.set(true)
            isValid=false
        }else {
            emailError.set(false)
        }
        if(userName.get().isNullOrBlank()){
            userNameError.set(true)
            isValid=false
        }else {
            userNameError.set(false)
        }
        if(password.get().isNullOrBlank()){
            passwordError.set(true)
            isValid=false
        }else {
            passwordError.set(false)
        }
        return isValid
    }
}