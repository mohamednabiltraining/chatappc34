package com.route.chatappc34.database.model

import android.text.format.DateUtils
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class Message(var id:String?=null,
              var messageText:String?=null,
              var senderName:String?=null,
              var senderId:String?=null,
              var roomId:String?=null,
              var time:Timestamp?=null
){
    fun formatTime():String{

       return ""+DateUtils.getRelativeTimeSpanString(
           time?.seconds?:0, Date().time, DateUtils.MINUTE_IN_MILLIS);
//        val date =  time?.toDate()
//        val simpleDateFormat = SimpleDateFormat("hh:mm:ss dd MMM")
//        return simpleDateFormat.format(date)
    }
}