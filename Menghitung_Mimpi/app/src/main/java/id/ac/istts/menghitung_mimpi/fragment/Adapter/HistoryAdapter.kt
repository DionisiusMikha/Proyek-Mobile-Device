package id.ac.istts.menghitung_mimpi.fragment.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.DanaDarurat
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Invest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Nikah

class HistoryAdapter(
    private val items: List<Any>,
    private val onClick: (String, Int) -> Unit
): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(val row:View) : RecyclerView.ViewHolder(row){
        val ivKategoriHistory: ImageView = row.findViewById(R.id.ivKategoriHistory)
        val tvKategoriHistory: TextView = row.findViewById(R.id.tvKategoriHistory)
        val layoutItem: ConstraintLayout = row.findViewById(R.id.layoutItem)
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
//        return when (viewType) {
//            TYPE_DANA_DARURAT -> DanaDaruratViewHolder(view)
//            TYPE_INVEST -> InvestViewHolder(view)
//            TYPE_NIKAH -> NikahViewHolder(view)
//            else -> throw IllegalArgumentException("Invalid view type")
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            R.layout.history_item, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        when (data) {
            is DanaDarurat -> {
                holder.tvKategoriHistory.text = "Dana Darurat"
                holder.ivKategoriHistory.setImageResource(R.drawable.danadarurat)
                holder.layoutItem.setOnClickListener{
                    onClick("darurat", position)
                }
            }
            is Invest -> {
                holder.tvKategoriHistory.text = "Investasi"
                holder.ivKategoriHistory.setImageResource(R.drawable.invest)
                holder.layoutItem.setOnClickListener{
                    onClick("investasi", position)
                }
            }
            is Nikah -> {
                holder.tvKategoriHistory.text = "Menikah"
                holder.ivKategoriHistory.setImageResource(R.drawable.nikah)
                holder.layoutItem.setOnClickListener{
                    onClick("nikah", position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (items[position]) {
//            is DanaDarurat -> TYPE_DANA_DARURAT
//            is Invest -> TYPE_INVEST
//            is Nikah -> TYPE_NIKAH
//            else -> throw IllegalArgumentException("Invalid item type at position $position")
//        }
//    }
//
//    // ViewHolders
//    inner class DanaDaruratViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val textViewDanaDarurat: TextView = itemView.findViewById(R.id.tvKategoriHistory)
//        private val ivKategoriHistory: ImageView = itemView.findViewById(R.id.ivKategoriHistory)
//
//        fun bind(danaDarurat: DanaDarurat) {
//            textViewDanaDarurat.text = "Dana Darurat"
//            ivKategoriHistory.setImageResource(R.drawable.history_dana_darurat)
//        }
//    }
//
//    inner class InvestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val textViewInvest: TextView = itemView.findViewById(R.id.tvKategoriHistory)
//        private val ivKategoriHistory: ImageView = itemView.findViewById(R.id.ivKategoriHistory)
//
//        fun bind(invest: Invest) {
//            textViewInvest.text = "Kalkulator Investasi"
//            ivKategoriHistory.setImageResource(R.drawable.history_kalkulator_investasi)
//        }
//    }
//
//    inner class NikahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val textViewNikah: TextView = itemView.findViewById(R.id.tvKategoriHistory)
//        private val ivKategoriHistory: ImageView = itemView.findViewById(R.id.ivKategoriHistory)
//
//        fun bind(nikah: Nikah) {
//            textViewNikah.text = "Menikah"
//            ivKategoriHistory.setImageResource(R.drawable.history_menikah)
//        }
//    }
}
