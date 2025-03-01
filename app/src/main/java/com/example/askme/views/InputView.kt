package com.example.askme.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun InputView(input: MutableStateFlow<String>, onDone: (String) -> Unit) {
    var value by remember { mutableStateOf(input.value) }

    OutlinedTextField(
        trailingIcon = {
            Icon(Icons.Filled.Close, "", modifier = Modifier.clickable { value = "" })
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Write your question here") },
        value = value,
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions(onDone = {
            onDone(value)  // Send updated value
        }),
        onValueChange = {
            value = it
            input.value = it  // Sync with MutableStateFlow
        }
    )
}
