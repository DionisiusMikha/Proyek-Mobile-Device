package id.ac.istts.menghitung_mimpi.data

import android.graphics.Bitmap

data class Chat (
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)