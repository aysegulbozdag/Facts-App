package com.example.randomfacts.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlin.collections.List


class SharedPre {

    fun setLists(context: Context, list: ArrayList<String>) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("LIST", json)
        editor.commit()
    }

    fun getList(context: Context): ArrayList<String> {
        var a: ArrayList<String> = ArrayList();
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = preferences.getString("LIST", null)
        val type = object : TypeToken<ArrayList<String>>() {}.type
        if (json != null) {
            a = gson.fromJson(json, type)
        }
        return a
    }
}