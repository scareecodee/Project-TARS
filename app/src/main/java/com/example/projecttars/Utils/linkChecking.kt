package com.example.projecttars.Utils

import android.util.Patterns

fun isLink(text: String): Boolean {
    return Patterns.WEB_URL.matcher(text).matches()
}
