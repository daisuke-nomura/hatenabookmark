package com.kyaracter.hatenabookmark.presentation.main.entity

import org.threeten.bp.LocalDateTime

data class RssItem(val name: String,
                   val date: LocalDateTime,
                   val description: String,
                   val url: String,
                   val thumbnail: String,
                   val bookmarkCount: Int)