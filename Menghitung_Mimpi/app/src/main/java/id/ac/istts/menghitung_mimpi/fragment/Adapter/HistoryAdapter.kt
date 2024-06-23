package id.ac.istts.menghitung_mimpi.fragment.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.DanaDarurat
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Invest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Nikah

class HistoryAdapter(private val items: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_DANA_DARURAT = 1
        private const val TYPE_INVEST = 2
        private const val TYPE_NIKAH = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return when (viewType) {
            TYPE_DANA_DARURAT -> DanaDaruratViewHolder(view)
            TYPE_INVEST -> InvestViewHolder(view)
            TYPE_NIKAH -> NikahViewHolder(view)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_DANA_DARURAT -> (holder as DanaDaruratViewHolder).bind(items[position] as DanaDarurat)
            TYPE_INVEST -> (holder as InvestViewHolder).bind(items[position] as Invest)
            TYPE_NIKAH -> (holder as NikahViewHolder).bind(items[position] as Nikah)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DanaDarurat -> TYPE_DANA_DARURAT
            is Invest -> TYPE_INVEST
            is Nikah -> TYPE_NIKAH
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    // ViewHolders
    inner class DanaDaruratViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewDanaDarurat: TextView = itemView.findViewById(R.id.tvKategoriHistory)
        private val ivKategoriHistory: ImageView = itemView.findViewById(R.id.ivKategoriHistory)

        fun bind(danaDarurat: DanaDarurat) {
            textViewDanaDarurat.text = "Dana Darurat"
            ivKategoriHistory.setImageResource(R.drawable.history_dana_darurat)
        }
    }

    inner class InvestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewInvest: TextView = itemView.findViewById(R.id.tvKategoriHistory)
        private val ivKategoriHistory: ImageView = itemView.findViewById(R.id.ivKategoriHistory)

        fun bind(invest: Invest) {
            textViewInvest.text = "Kalkulator Investasi"
            ivKategoriHistory.setImageResource(R.drawable.history_kalkulator_investasi)
        }
    }

    inner class NikahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNikah: TextView = itemView.findViewById(R.id.tvKategoriHistory)
        private val ivKategoriHistory: ImageView = itemView.findViewById(R.id.ivKategoriHistory)

        fun bind(nikah: Nikah) {
            textViewNikah.text = "Menikah"
            ivKategoriHistory.setImageResource(R.drawable.history_menikah)
        }
    }
}
