package com.route.chatappc34.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.route.chatappc34.base.BaseViewModel
import com.route.chatappc34.database.dao.RoomDao
import com.route.chatappc34.database.model.Room

class AddRoomViewModel:BaseViewModel<Navigator>() {
    val roomName = ObservableField<String>()
    val roomDesc = ObservableField<String>()
    val roomAdded = MutableLiveData<Boolean>()
    fun addRoom(){
        if(!isValid())return
        showLoading.value=true;
        val room = Room(name = roomName.get(),desc = roomDesc.get())
        RoomDao.insertRoom(room, OnCompleteListener{task->
            showLoading.value=false
            if(task.isSuccessful){
                roomAdded.value=true
            }else{
                messageLiveData.value = task.exception?.localizedMessage
            }
        })
    }
    fun isValid():Boolean{
        if(roomName.get().isNullOrBlank()){
            messageLiveData.value="please enter a valid room name"
            return false
        }
        if(roomDesc.get().isNullOrBlank()){
            messageLiveData.value="please enter a valid room desc"
            return false
        }
        return true
    }
}