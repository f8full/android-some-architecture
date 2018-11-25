package com.f8full.somearchitecture.ui.connectivityinfo

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.f8full.somearchitecture.ConnectivityWatcher
import com.f8full.somearchitecture.R

class ConnectivityInfoFragment : Fragment() {

    companion object {
        fun newInstance() = ConnectivityInfoFragment()
    }

    private lateinit var viewModel: ConnectivityInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.connectivity_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConnectivityInfoViewModel::class.java)

        /*ConnectivityWatcher.init(viewModel,
            NetworkRequest.Builder(),
            this.activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager,
            ConnectivityWatcher.MyConnectivityManagerNetworkCallback())*/
    }

}
