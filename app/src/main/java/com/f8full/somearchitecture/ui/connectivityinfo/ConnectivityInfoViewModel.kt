package com.f8full.somearchitecture.ui.connectivityinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ConnectivityInfoViewModel : ViewModel() {

    private val mNetworkConnectivityAvailable = MutableLiveData<Boolean>()

    fun postNetworkConnectivityAvailable(toPost: Boolean) {
        mNetworkConnectivityAvailable.postValue(toPost)
    }

    fun setNetworkConnectivityAvailable(toSet: Boolean) {
        mNetworkConnectivityAvailable.value = toSet
    }

    val isNetworkConnectivityAvailable: LiveData<Boolean>
        get() = mNetworkConnectivityAvailable
}
