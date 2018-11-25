package com.f8full.somearchitecture

import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build.VERSION_CODES.M
import com.f8full.somearchitecture.ui.connectivityinfo.ConnectivityInfoViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadow.api.Shadow

@RunWith(RobolectricTestRunner::class)
@Config(minSdk = M)
class ConnectivityWatcherTest {

    private var mockMainActivityModel: ConnectivityInfoViewModel? = null
    private var mockConnectivityManagerNetworkCallback: ConnectivityWatcher.MyConnectivityManagerNetworkCallback? = null

    private var connectivityManager: ConnectivityManager? = null
    private var networkRequestBuilder: NetworkRequest.Builder? = null

    @Before
    fun setup() {

        mockMainActivityModel = ConnectivityInfoViewModel()
        mockConnectivityManagerNetworkCallback = ConnectivityWatcher.MyConnectivityManagerNetworkCallback()

        connectivityManager = Shadow.newInstanceOf(ConnectivityManager::class.java)

        networkRequestBuilder = Shadow.newInstanceOf(NetworkRequest.Builder::class.java)

        ConnectivityWatcher.reinit(mockMainActivityModel!!, networkRequestBuilder!!, connectivityManager!!,
            mockConnectivityManagerNetworkCallback!!)
    }

    @Test
    fun onNetworkLostCallbackPostToModel() {

        mockMainActivityModel!!.setNetworkConnectivityAvailable(true)

        mockConnectivityManagerNetworkCallback!!.onLost(null)

        assertNotNull(mockMainActivityModel!!.isNetworkConnectivityAvailable.value)

        assertFalse(mockMainActivityModel!!.isNetworkConnectivityAvailable.value!!)

    }


    @Test
    fun onNetworkAvailableCallbackPostToModel() {

        mockMainActivityModel!!.setNetworkConnectivityAvailable(false)

        mockConnectivityManagerNetworkCallback!!.onAvailable(null)

        assertNotNull(mockMainActivityModel!!.isNetworkConnectivityAvailable.value)

        assertTrue(mockMainActivityModel!!.isNetworkConnectivityAvailable.value!!)

    }
}
