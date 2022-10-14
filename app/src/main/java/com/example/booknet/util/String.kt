package com.example.booknet.util

import android.content.Context
import android.provider.Settings
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

fun deviceId(ctx: Context): String =
    Settings.Secure.getString(ctx.contentResolver, Settings.Secure.ANDROID_ID)
