package id.ac.istts.menghitung_mimpi.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.istts.menghitung_mimpi.data.Chat
import id.ac.istts.menghitung_mimpi.data.ChatData
import id.ac.istts.menghitung_mimpi.layout.ChatState
import id.ac.istts.menghitung_mimpi.layout.ChatUiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    fun onEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.UpdatePrompt -> {
                _chatState.update { it.copy(prompt = event.newPrompt) }
            }
            is ChatUiEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt, event.bitmap)

                    if (event.bitmap != null) {
                        getResponse(event.prompt)
                    } else {
                        getResponse(event.prompt)
                    }
                }
            }
        }
    }

    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                    chatList =
                            it.chatList.toMutableList().apply {
                                add(0, Chat(prompt, bitmap, true))
                            },
                    prompt = "",
                    bitmap = null,
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            val chat = ChatData.getChatResponse(prompt)
            _chatState.update {
                it.copy(
                        chatList = it.chatList.toMutableList().apply { add(0, chat) },
                )
            }
        }
    }
}
