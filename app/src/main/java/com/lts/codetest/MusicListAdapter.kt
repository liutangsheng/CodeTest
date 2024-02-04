package com.lts.codetest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lts.codetest.data.Music

/**
 * <pre>
 *     author : liutangsheng
 *     time   : 2024/02/04
 * </pre>
 */
class MusicListAdapter : RecyclerView.Adapter<MusicViewHolder>(), Filterable {

    private var filterList: MutableList<Music> = ArrayList()
    private var sourceList: MutableList<Music> = ArrayList()


    fun setSourceList(list: MutableList<Music>) {
        sourceList = list
        filterList = list
    }

    fun sortByPrice() {
        filterList.sortBy { it.trackPrice }
        notifyDataSetChanged()
    }

    fun offSort() {
        filterList.sortBy { it.releaseDate }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.setMusic(filterList[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterText = constraint.toString()
                filterList = if (filterText.isEmpty()) {
                    sourceList
                } else {
                    val arrayList = mutableListOf<Music>()
                    for (music in sourceList) {
                        if (music.artistName.contains(filterText) || music.trackName.contains(filterText)) {
                            arrayList.add(music)
                        }
                    }
                    arrayList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as MutableList<Music>
//                onFilterListener?.invoke(filterList)
                notifyDataSetChanged()
            }
        }
    }
}

class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setMusic(music: Music) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageview)
        val trackName = itemView.findViewById<TextView>(R.id.track_name)
        val artistName = itemView.findViewById<TextView>(R.id.artist_name)
        val price = itemView.findViewById<TextView>(R.id.price)

        Glide.with(itemView.context).load(music.artworkUrl100).into(imageView)
        trackName.text = music.trackName
        artistName.text = music.artistName
        price.text = "Price:$${music.trackPrice}"
    }

}