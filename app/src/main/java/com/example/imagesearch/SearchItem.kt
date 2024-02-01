package com.example.imagesearch

data class SearchItem(
    var title: String,
    var time: String,
    var url: String,
    var isLike: Boolean = false
)

