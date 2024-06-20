package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import id.ac.istts.menghitung_mimpi.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val model =
        GenerativeModel(
                "gemini-1.5-flash",
                // akses dari secret.properties
                BuildConfig.GEMINI_API_KEY, // cara add secret.properties: https://developer.android.com/studio/build/secrets-gradle-plugin
                generationConfig =
                        GenerativeModel.GenerationConfig {
                            temperature = 1f
                            topK = 64
                            topP = 0.95f
                            maxOutputTokens = 8192
                            responseMimeType = "text/plain"
                        }
        )

val chatHistory =
        listOf(
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

class ChatBotFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_bot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startChat()
    }

    private fun startChat() {
        CoroutineScope(Dispatchers.IO).launch {
            val chat = model.startChat(chatHistory)
            // Replace "INSERT_INPUT_HERE" with your input
            val response = chat.sendMessage("INSERT_INPUT_HERE")
            // Get the first text part of the first candidate
            println(response.text)
            // Alternatively
            println(response.candidates.first().content.parts.first().asTextOrNull())
        }
    }
}
