package com.kyaracter.hatenabookmark.translator

import com.kyaracter.hatenabookmark.data.remote.entity.Rss
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun Rss.toRssItem(): List<RssItem> {
    return this
        .items!!
        .map {
            RssItem(it.title ?: "",
                LocalDateTime.parse(it.date, DateTimeFormatter.ISO_DATE_TIME),
                it.description ?: "",
                it.link!!,
                it.thumbnail ?: "",
                it.bookmarkCount)
        }
}