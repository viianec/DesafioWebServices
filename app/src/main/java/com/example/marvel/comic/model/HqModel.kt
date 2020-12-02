package com.example.marvel.comic.model

import com.example.marvel.data.model.ThumbnailModel

class HqModel (
        val id: Int?,
        val title: String?,
        val description: String?,
        val pageCount: Int?,
        val dates: List<HqDate>?,
        val prices: List<HqPrice>?,
        val thumbnail: ThumbnailModel?,
        val images: List<ThumbnailModel>?
)