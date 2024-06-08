package id.ac.istts.menghitung_mimpi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(
    private val context: Context,
    private val images: IntArray,
    private val texts: IntArray
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.slider_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], texts[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val slideTitleImage: ImageView = itemView.findViewById(R.id.IVSliderLayout)
        private val slideText: TextView = itemView.findViewById(R.id.tvTextSliderLayout)

        fun bind(imageResId: Int, textResId: Int) {
            slideTitleImage.setImageResource(imageResId)
            slideText.text = context.getString(textResId)
        }
    }
}
