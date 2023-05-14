package com.example.laba2.Data

import org.json.JSONArray

sealed class CardItem {
    data class TextOnImage(
        val img: String?,
        val title: String,
        val subtitle: String,
        val hasBag: String?
    ) : CardItem()

    data class TextOverImage(
        val img: String?,
        val title: String,
        val subtitle: String
    ) : CardItem()

    data class TextOnly(
        val title: String,
        val subtitle: String
    ) : CardItem()

    data class CircularImage(
        val img: String?,
        val title: String,
        val subtitle: String,
        val isCircle: Boolean
    ) : CardItem()

    companion object {
        fun fromJson(json: String): List<CardItem> {
            val cardList = mutableListOf<CardItem>()
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val img = jsonObject.optString("img", null)
                val title = jsonObject.optString("title", "")
                val subtitle = jsonObject.optString("subtitle", "")

                val cardItem = when {
                    jsonObject.has("hasBag") -> {
                        val hasBag = jsonObject.optString("hasBag")
                        TextOnImage(img, title, subtitle, hasBag)
                    }
                    jsonObject.has("isCircle") -> {
                        val isCircle = jsonObject.optBoolean("isCircle")
                        CircularImage(img, title, subtitle, isCircle)
                    }
                    img != null -> TextOverImage(img, title, subtitle)
                    else -> TextOnly(title, subtitle)
                }

                cardList.add(cardItem)
            }

            return cardList
        }
    }
}
