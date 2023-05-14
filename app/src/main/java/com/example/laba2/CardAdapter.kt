package com.example.laba2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.Data.CardItem
import com.example.laba2.Holders.CircularImageViewHolder
import com.example.laba2.Holders.TextOnImageViewHolder
import com.example.laba2.Holders.TextOnlyViewHolder
import com.example.laba2.Holders.TextOverImageViewHolder

class CardAdapter : ListAdapter<CardItem, RecyclerView.ViewHolder>(CardItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView: View = when (viewType) {
            VIEW_TYPE_TEXT_ON_IMAGE -> inflater.inflate(R.layout.item_text_on_image, parent, false)
            VIEW_TYPE_TEXT_OVER_IMAGE -> inflater.inflate(R.layout.item_text_over_image, parent, false)
            VIEW_TYPE_TEXT_ONLY -> inflater.inflate(R.layout.item_text_only, parent, false)
                VIEW_TYPE_CIRCULAR_IMAGE -> inflater.inflate(R.layout.item_circular_image, parent, false)
            else -> throw IllegalArgumentException("Invalid view type")
        }
        val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
        val marginStart = itemView.context.resources.getDimensionPixelSize(R.dimen.item_margin_start)
        val marginEnd = itemView.context.resources.getDimensionPixelSize(R.dimen.item_margin_end)
        val marginTop = itemView.context.resources.getDimensionPixelSize(R.dimen.item_margin_top)
        val marginBottom = itemView.context.resources.getDimensionPixelSize(R.dimen.item_margin_bottom)

        layoutParams.setMargins(marginStart, marginTop, marginEnd, marginBottom)
        itemView.layoutParams = layoutParams


        return when (viewType) {
            VIEW_TYPE_TEXT_ON_IMAGE -> TextOnImageViewHolder(itemView)
            VIEW_TYPE_TEXT_OVER_IMAGE -> TextOverImageViewHolder(itemView)
            VIEW_TYPE_TEXT_ONLY -> TextOnlyViewHolder(itemView)
            VIEW_TYPE_CIRCULAR_IMAGE -> CircularImageViewHolder(itemView)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cardItem = getItem(position)
        when (holder) {
            is TextOnImageViewHolder -> holder.bind(cardItem as CardItem.TextOnImage)
            is TextOverImageViewHolder -> holder.bind(cardItem as CardItem.TextOverImage)
            is TextOnlyViewHolder -> holder.bind(cardItem as CardItem.TextOnly)
            is CircularImageViewHolder -> holder.bind(cardItem as CardItem.CircularImage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val cardItem = getItem(position)
        return when (cardItem) {
            is CardItem.TextOnImage -> VIEW_TYPE_TEXT_ON_IMAGE
            is CardItem.TextOverImage -> VIEW_TYPE_TEXT_OVER_IMAGE
            is CardItem.TextOnly -> VIEW_TYPE_TEXT_ONLY
            is CardItem.CircularImage -> VIEW_TYPE_CIRCULAR_IMAGE
        }
    }

    companion object {
        private const val VIEW_TYPE_TEXT_ON_IMAGE = 1
        private const val VIEW_TYPE_TEXT_OVER_IMAGE = 2
        private const val VIEW_TYPE_TEXT_ONLY = 3
        private const val VIEW_TYPE_CIRCULAR_IMAGE = 4
    }

    private class CardItemDiffCallback : DiffUtil.ItemCallback<CardItem>() {
        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }
    }
}
