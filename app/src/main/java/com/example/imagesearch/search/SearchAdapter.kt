package com.example.imagesearch.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.MainActivity
import com.example.imagesearch.R
import com.example.imagesearch.SearchItem
import com.example.imagesearch.databinding.ResultItemBinding

class SearchAdapter(private val mContext: Context) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    private var items = mutableListOf<SearchItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(mContext)
            .load(currentItem.url)
            .into(holder.itemImg)

        if (currentItem.isLike) {
            holder.itemFav.setImageResource(R.drawable.ic_favorite_selected)
        } else {
            holder.itemFav.setImageResource(R.drawable.ic_favorite)
        }
        holder.itemTitle.text = currentItem.title
        holder.itemTime.text = currentItem.time
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: ResultItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        var item: ConstraintLayout = binding.clItem
        var itemImg: ImageView = binding.ivItemImage
        var itemTitle: TextView = binding.tvItemTitle
        var itemTime: TextView = binding.tvItemTime
        var itemFav: ImageView = binding.ivItemFavorite

        init {
            item.setOnClickListener(this)
            itemImg.setOnClickListener(this)
            itemFav.setImageResource(R.drawable.ic_favorite)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val item = items[position]

            item.isLike = !item.isLike

            if (item.isLike) {
                (mContext as MainActivity).addLikedItem(item)
            } else {
                (mContext as MainActivity).removeLikedItem(item)
            }

            notifyItemChanged(position)

        }
    }

}
