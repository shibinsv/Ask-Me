package com.example.askme.utils

import android.content.Context
import com.example.askme.Constants
import com.example.askme.utils.HelperFunctions.getGoogleCredentials
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.cloud.dialogflow.cx.v3.DetectIntentRequest
import com.google.cloud.dialogflow.cx.v3.QueryInput
import com.google.cloud.dialogflow.cx.v3.SessionName
import com.google.cloud.dialogflow.cx.v3.SessionsClient
import com.google.cloud.dialogflow.cx.v3.SessionsSettings
import com.google.cloud.dialogflow.cx.v3.TextInput
import java.util.UUID

object PromptUtils {
    fun getMessage(context: Context, prompt: String): String {
        return try {
            val credentials = getGoogleCredentials(context)
            val settings = SessionsSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build()

            val sessionsClient = SessionsClient.create(settings)
            val sessionId = UUID.randomUUID().toString()

            val session = SessionName.of(
                Constants.PROJECT_ID,
                Constants.LOCATION,
                Constants.AGENT_ID,
                sessionId
            )

            val textInput = TextInput.newBuilder().setText(prompt).build()
            val queryInput =
                QueryInput.newBuilder().setText(textInput).setLanguageCode("EN").build()

            val detectIntentRequest = DetectIntentRequest.newBuilder()
                .setSession(session.toString())
                .setQueryInput(queryInput)
                .build()

            val response = sessionsClient.detectIntent(detectIntentRequest)
            sessionsClient.close()

            val responseText = response.queryResult.responseMessagesList.first().text.getText(0)
            responseText
        } catch (e: Exception) {
            println(e.message)
            ""
        }
    }
}