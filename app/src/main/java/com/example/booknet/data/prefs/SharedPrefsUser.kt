package com.example.booknet.data.prefs

import android.content.Context

const val PREFS_FILENAME_USER = "shared_prefs_user"

private const val KEY_USER_TOKEN = "KEY_USER_TOKEN"
private const val KEY_USER_LOGIN = "KEY_USER_LOGIN"

class SharedPrefsUser(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_FILENAME_USER, Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getValue(KEY_USER_TOKEN, "dtknyWZS7VmiR_mV3DLYRIPYdej8L9ps")
        set(value) = prefs.setValue(KEY_USER_TOKEN, value)

    var login: Boolean?
        get() = prefs.getValue(KEY_USER_LOGIN, false)
        set(value) = prefs.setValue(KEY_USER_LOGIN, value)

}