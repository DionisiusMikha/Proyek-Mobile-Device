package id.ac.istts.menghitung_mimpi.layout

import android.graphics.Bitmap
import id.ac.istts.menghitung_mimpi.data.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)