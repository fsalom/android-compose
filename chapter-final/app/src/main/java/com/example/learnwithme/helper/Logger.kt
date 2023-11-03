package com.example.learnwithme.helper

import android.util.Log
import retrofit2.Response

class Logger() {
    private val TAG = "MY_APP"

    fun log(message: String) {
        Log.i(TAG, message)
    }

    fun <T>network(response: Response<T>) {
        Log.i(TAG, "HEADERS: "+ response.headers().toString())
        Log.i(TAG, "STATUS CODE: " + response.code().toString())
        Log.i(TAG, "BODY: " + response.body().toString())
    }
}
