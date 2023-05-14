package com.example.laba2.Holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.Data.CardItem
import com.example.laba2.R

class TextOnlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle_text_view)

    fun bind(cardItem: CardItem.TextOnly) {
        titleTextView.text = cardItem.title
        subtitleTextView.text = cardItem.subtitle
    }
}
