package com.example.askme

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.askme.utils.PromptUtils
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    val input = MutableStateFlow("")
    val output = MutableStateFlow("")

    val isLoading = MutableStateFlow(false)
    fun setLoading(value: Boolean) {
        isLoading.value = value
    }

    fun updateOutput(value: String) {
        output.value = value
    }

    fun getMessage(context: Context, prompt: String): String {
        val output = PromptUtils.getMessage(context, prompt)
        setLoading(false)
        return output
    }
}
