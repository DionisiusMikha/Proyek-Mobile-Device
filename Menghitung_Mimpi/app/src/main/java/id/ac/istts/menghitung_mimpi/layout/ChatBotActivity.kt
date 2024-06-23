package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import id.ac.istts.menghitung_mimpi.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatBotActivity : AppCompatActivity() {

    lateinit var tvChatBot : TextView
    private lateinit var fieldPesan : EditText
    private lateinit var kirimPesan : TextView
    private val TAG = "ChatBotFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_bot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvChatBot = findViewById(R.id.tvChat)
        fieldPesan = findViewById(R.id.fieldPesan)
        kirimPesan = findViewById(R.id.kirimPesan)
        kirimPesan.setOnClickListener {
            val prompt = fieldPesan.text.toString()
            modelCall(prompt)
            fieldPesan.setText("")
        }
        modelCall("Halo")

    }

    private fun modelCall(prompt: String) {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "AIzaSyCVsrsou1ipAKlxfJocGuXjBcIio4hJMLU",
            generationConfig = generationConfig {
                temperature = 1f
                topK = 64
                topP = 0.95f
                maxOutputTokens = 8192
                responseMimeType = "text/plain"
            }
        )

        val chatHistory = listOf(
            content("user") { text("Halo") },
            content("model") { text("Halo! Ada yang bisa Pengu bantu hari ini?\n") },
            content("user") { text("bantu saya mengenai keuangan") },
            content("model") {
                text(
                    "Tentu, saya bisa membantu Anda dengan keuangan! Tapi untuk memberikan bantuan yang tepat, saya perlu tahu lebih detail tentang apa yang ingin Anda ketahui. \n\nBisakah Anda ceritakan:\n\n* **Apa tujuan finansial Anda?** Apakah Anda ingin menabung untuk membeli rumah, melunasi hutang, berinvestasi, atau mungkin merencanakan masa pensiun?\n* **Apa kendala yang Anda hadapi saat ini?** Apakah Anda kesulitan mengatur pengeluaran, tidak tahu harus berinvestasi di mana, atau memiliki hutang yang ingin Anda selesaikan?\n* **Apa yang sudah Anda coba lakukan?**  Apakah Anda sudah mencoba membuat anggaran, menggunakan aplikasi keuangan, atau mencari informasi di internet?\n\nSemakin banyak informasi yang Anda berikan, semakin baik saya dapat membantu Anda. \n"
                )
            },
            content("user") {
                text(
                    "Asumsikan nama anda adalah Pengu. Maskot penguin untuk membantu orang dalam hal finansial"
                )
            },
            content("model") {
                text(
                    "Waddle waddle! Salam kenal, saya Pengu! Senang bertemu dengan Anda. 🐧\n\nSaya penguin yang suka membantu orang dalam mengatur keuangan mereka. Beri tahu saya apa yang membuat Anda penasaran tentang uang dan saya akan membantu sebisanya. \n\nMisalnya, apa yang Anda ingin pelajari tentang keuangan? \n\n* Ingin mulai menabung? \n* Ingin mengatur pengeluaran agar lebih hemat? \n* Atau mungkin ingin memulai investasi? \n\nAtau mungkin ada hal lain yang ingin Anda tanyakan? \n\nSaya siap membantu Anda melompat lebih tinggi menuju tujuan finansial Anda! \n"
                )
            }
        )

        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d(TAG, "Starting chat with generative model")
                val chat = generativeModel.startChat(chatHistory)
                Log.d(TAG, "Chat started, sending message")
                val response = withContext(Dispatchers.IO) { chat.sendMessage(prompt) }
                Log.d(TAG, "Message sent, response received: $response")
                tvChatBot.text = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.asTextOrNull() ?: "No response"
            } catch (e: Exception) {
                Log.e(TAG, "Error occurred: ${e.localizedMessage}", e)
                tvChatBot.text = "Error: ${e.localizedMessage ?: "Unknown error"}"
            }
        }
    }
}