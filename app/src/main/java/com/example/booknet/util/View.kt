package com.example.booknet.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(text: String = "coming soon...") {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}