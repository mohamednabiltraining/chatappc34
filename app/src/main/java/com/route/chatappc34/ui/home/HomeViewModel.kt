package com.route.chatappc34.ui.home;

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.route.chatappc34.base.BaseViewModel
import com.route.chatappc34.database.dao.RoomDao
import com.route.chatappc34.database.model.Room

class HomeViewModel :BaseViewModel<Navigator>(){

 val roomsLiveData = MutableLiveData<List<Room>>()
  fun getRoomsList(){
   RoomDao.getRoomsList(OnSuccessListener {result->
    val roomsList : MutableList<Room> = mutableListOf()
    for(document in result){
     val room = document.toObject(Room::class.java)
     roomsList.add(room)
    }
    roomsLiveData.value=roomsList
   },
   OnFailureListener {
    messageLiveData.value = it.localizedMessage
   })
  }

  fun gotoAddRoom(){
   navigator?.openAddRoom()
  }
 }

