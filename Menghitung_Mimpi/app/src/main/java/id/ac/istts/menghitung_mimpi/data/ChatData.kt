package id.ac.istts.menghitung_mimpi.data

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {
    val api_key = "AIzaSyCVsrsou1ipAKlxfJocGuXjBcIio4hJMLU"

    suspend fun getChatResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(modelName = "gemini-pro", apiKey = api_key)
        try {
            val response = withContext(Dispatchers.IO) { generativeModel.generateContent(prompt) }

            return Chat(
                    prompt = response.text ?: "Sorry, I can't process your request right now",
                    bitmap = null,
                    isFromUser = false
            )
        } catch (e: Exception) {
            return Chat(
                    prompt = e.message ?: "Sorry, I can't process your request right now",
                    bitmap = null,
                    isFromUser = false
            )
        }
    }
}
