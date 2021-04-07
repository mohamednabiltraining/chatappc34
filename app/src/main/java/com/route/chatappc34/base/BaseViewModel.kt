package com.route.chatappc34.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N> :ViewModel() {
    var navigator:N?=null
    val messageLiveData = MutableLiveData<String>()// all message configs
    val showLoading = MutableLiveData<Boolean>()
}