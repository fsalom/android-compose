package com.example.learnwithme.manager.cache

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object SharedPreferenceManager {

    lateinit var preferences: SharedPreferences
    private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"

    /**
     * Call this first before retrieving or saving object.
     *
     * @param application Instance of application class
     */
    fun with(application: Application) {
        preferences = application.getSharedPreferences(
            PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> save(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        preferences.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> retrieve(key: String): T? {
        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}