package com.example.askme.utils

import android.content.Context
import com.google.auth.oauth2.GoogleCredentials

object HelperFunctions {

    fun getGoogleCredentials(context: Context): GoogleCredentials {
        val inputStream = context.assets.open("service.json")
        return GoogleCredentials.fromStream(inputStream)
    }

}