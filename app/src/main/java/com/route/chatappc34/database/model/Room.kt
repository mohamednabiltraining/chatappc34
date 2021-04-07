package com.route.chatappc34.database.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room (var id:String?=null,
                 var name:String?=null,
                 var desc:String?=null):Parcelable