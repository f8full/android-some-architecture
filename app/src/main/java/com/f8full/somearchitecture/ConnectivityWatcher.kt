package com.f8full.somearchitecture

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import com.f8full.somearchitecture.ui.connectivityinfo.ConnectivityInfoViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityWatcher {

    private val TAG = this.javaClass::getSimpleName

    inner class MyConnectivityManagerNetworkCallback : ConnectivityManager.NetworkCallback() {

        private var availabilityByNetworkToStringMap : HashMap<String, Boolean> = HashMap()

        @Suppress("unused") //TODO: adapt unit tests to inspect this map
        fun getNetworkMap():HashMap<String,Boolean>{
            return availabilityByNetworkToStringMap
        }

        override fun onAvailable(network: Network?) {
            availabilityByNetworkToStringMap[network.toString()] = true
            updateNetworkAvailability()
        }

        override fun onLost(network: Network?) {
            availabilityByNetworkToStringMap[network.toString()] = false
            updateNetworkAvailability()
        }

        //Sets availability to true if at least one network is available, false otherwise
        private fun updateNetworkAvailability(){
            var atLeastOneAvailable = false

            for (networkAvailable in availabilityByNetworkToStringMap.values){
                atLeastOneAvailable = atLeastOneAvailable || networkAvailable
            }


            this@ConnectivityWatcher.connInfoFragmentModel.postNetworkConnectivityAvailable(atLeastOneAvailable)
            //connInfoFragmentModel
        }
    }

    private val connInfoFragmentModel: ConnectivityInfoViewModel

    private val connManagerNetworkCallback: ConnectivityManager.NetworkCallback

    //To protect against external construction
    //@Suppress("unused")
    //private constructor()

    @Inject
    constructor(connectivityInfoFragmentModel: ConnectivityInfoViewModel,
                        networkRequestBuilder: NetworkRequest.Builder,
                        connectivityManager: ConnectivityManager,
                        connectivityManagerNetworkCallback: MyConnectivityManagerNetworkCallback) {

        connInfoFragmentModel = connectivityInfoFragmentModel

        connManagerNetworkCallback = connectivityManagerNetworkCallback

        connectivityManager.registerNetworkCallback(
            networkRequestBuilder.build(),
            connManagerNetworkCallback)
    }

    /*companion object {


        private var mInstance: ConnectivityWatcher? = null




        /*fun init(mainActivityModel: ConnectivityInfoViewModel,
                 networkRequestBuilder: NetworkRequest.Builder,
                 connectivityManager: ConnectivityManager,
                 connectivityManagerNetworkCallback: MyConnectivityManagerNetworkCallback) {

            if (mInstance == null)
                mInstance = ConnectivityWatcher(mainActivityModel, networkRequestBuilder, connectivityManager, connectivityManagerNetworkCallback)
        }

        fun reinit(mainActivityViewModel: ConnectivityInfoViewModel,
                   networkRequestBuilder: NetworkRequest.Builder,
                   connectivityManager: ConnectivityManager,
                   connectivityManagerNetworkCallback: MyConnectivityManagerNetworkCallback) {
            Log.w(TAG, "reinit shouldn't be called in normal execution context")

            if (mInstance == null)
                init(mainActivityViewModel, networkRequestBuilder, connectivityManager, connectivityManagerNetworkCallback)
            else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    connectivityManager.unregisterNetworkCallback(connManagerNetworkCallback)
                }

                mInstance = ConnectivityWatcher(mainActivityViewModel, networkRequestBuilder, connectivityManager, connectivityManagerNetworkCallback)
            }
        }*/
    }*/
}