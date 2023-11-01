package kedaiapps.projeku.testandroidsalt.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kedaiapps.projeku.testandroidsalt.R
import kedaiapps.projeku.testandroidsalt.databinding.ItemHomeBinding
import kedaiapps.projeku.testandroidsalt.ext.getformatDateTime
import kedaiapps.projeku.testandroidsalt.ext.inflate
import kedaiapps.projeku.testandroidsalt.services.entity.ResponseHome

class AdapterHome (
    private val onClick: (ResponseHome) -> Unit
) : RecyclerView.Adapter<AdapterHome.ViewHolder>() {

    var items: MutableList<ResponseHome> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_home))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(items.getOrNull(position)!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView){
        private var binding = ItemHomeBinding.bind(containerView)

        fun bind(data: ResponseHome){
            with(binding){

                Glide.with(itemView.rootView).load(data.urlToImage)
                    .apply(
                        RequestOptions()
                            .transform(RoundedCorners(16))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .dontAnimate()
                    ).into(binding.image)

                binding.judul.text = data.title
                binding.date.text = getformatDateTime(data.publishedAt, "dd MMMM yyyy")
                binding.author.text = data.author
                binding.description.text = data.description

                line.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    fun insertData(data : List<ResponseHome>){
        data.forEach {
            items.add(it)
            notifyDataSetChanged()
        }
    }

    fun clearData() {
        if (items.isNotEmpty()) {
            items.clear()
            notifyDataSetChanged()
        }
    }
}