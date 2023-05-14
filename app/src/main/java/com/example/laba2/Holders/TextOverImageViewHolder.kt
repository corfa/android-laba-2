package com.example.laba2.Holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.laba2.Data.CardItem
import com.example.laba2.R

class TextOverImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle_text_view)
    private val imageView: ImageView = itemView.findViewById(R.id.image_view)

    fun bind(cardItem: CardItem.TextOverImage) {
        titleTextView.text = cardItem.title
        subtitleTextView.text = cardItem.subtitle

        Glide.with(itemView)
            .load(cardItem.img)
            .apply(RequestOptions().centerCrop())
            .into(imageView)
    }
}
