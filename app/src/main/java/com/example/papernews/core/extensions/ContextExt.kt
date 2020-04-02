package com.example.papernews.core.extensions

import android.content.Context
import android.net.ConnectivityManager


fun Context.networkConnection(): Boolean {
    val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = conManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}
