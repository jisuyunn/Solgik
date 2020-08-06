package com.example.solgikb.data.source

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.solgikb.data.model.User
import com.example.solgikb.utils.*

class PreferenceSource(private val context: Context) {

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun saveUser(user: User) {
        preference.edit()
                .putString(PREF_KEY_UID, user.UId)
                .putString(PREF_KEY_EMAIL, user.email)
                .putString(PREF_KEY_NAME, user.name)
                .putString(PREF_KEY_ADDRESS, user.address)
                .putString(PREF_KEY_PHONE_NUM, user.phoneNum)
                .putString(PREF_KEY_STATUS, user.status)
                .putString(PREF_KEY_OVERDUE, user.overdue)
                .apply()
    }

    fun getUser(): User {
        return User(preference.getString(PREF_KEY_UID, "").toString(),
                preference.getString(PREF_KEY_EMAIL, "").toString(),
                preference.getString(PREF_KEY_NAME, "").toString(),
                preference.getString(PREF_KEY_ADDRESS, "").toString(),
                preference.getString(PREF_KEY_PHONE_NUM, "").toString(),
                preference.getString(PREF_KEY_STATUS, "").toString(),
                preference.getString(PREF_KEY_OVERDUE, "").toString())
    }

}