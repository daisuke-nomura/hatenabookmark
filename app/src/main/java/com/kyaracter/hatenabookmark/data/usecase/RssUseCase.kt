package com.kyaracter.hatenabookmark.data.usecase

import com.jakewharton.rxrelay2.Relay
import com.kyaracter.hatenabookmark.data.repository.CATEGORY
import com.kyaracter.hatenabookmark.data.repository.RssRepository
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RssUseCase @Inject constructor(
    private val rssRepository: RssRepository
) {

    fun fetch(relay: Relay<List<RssItem>>, category: CATEGORY): Single<Int> {
        return rssRepository
            .fetch(relay, category)
            .map { it.items!!.count() }
    }
}