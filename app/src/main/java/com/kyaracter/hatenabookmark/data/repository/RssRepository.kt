package com.kyaracter.hatenabookmark.data.repository

import com.jakewharton.rxrelay2.Relay
import com.kyaracter.hatenabookmark.data.remote.entity.Rss
import com.kyaracter.hatenabookmark.data.remote.service.RssService
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import com.kyaracter.hatenabookmark.translator.toRssItem
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RssRepository @Inject constructor(
    private val rssService: RssService
) {

    fun fetch(relay: Relay<List<RssItem>>, category: CATEGORY): Single<Rss> {
        return target(category)
            .doOnSuccess {
                // 受け取ったRelayに流すのは奇妙に感じられるが、
                // 今回のアプリはViewModel/UseCase/Repositoryに対してFragmentが多数存在する.
                // Fragmentが多の場合にRequest/Responseを分離するには、
                // RelayをFragmentから貰うと、間違いがない実装にできる.
                relay.accept(it.toRssItem())
            }
            .doOnError {
                Timber.e(it)
            }
    }

    private fun target(category: CATEGORY): Single<Rss> {
        // 通信先が固定なので、選択する.
        // 個別のRSSを呼ぶ場合は、RssServiceで@Urlアノテーションを使う.
        return when(category) {
            CATEGORY.GENERAL_HOT_ENTRY -> rssService.generalHotEntry()
            CATEGORY.GENERAL_ENTRY_LIST -> rssService.generalEntryList()
            CATEGORY.SOCIAL_HOT_ENTRY -> rssService.socialHotEntry()
            CATEGORY.SOCIAL_ENTRY_LIST -> rssService.socialEntryList()
            CATEGORY.ECONOMICS_HOT_ENTRY -> rssService.economicsHotEntry()
            CATEGORY.ECONOMICS_ENTRY_LIST -> rssService.economicsEntryList()
            CATEGORY.LIFE_HOT_ENTRY -> rssService.lifeHotEntry()
            CATEGORY.LIFE_ENTRY_LIST -> rssService.lifeEntryList()
            CATEGORY.KNOWLEDGE_HOT_ENTRY -> rssService.knowledgeHotEntry()
            CATEGORY.KNOWLEDGE_ENTRY_LIST -> rssService.knowledgeEntryList()
            CATEGORY.IT_HOT_ENTRY -> rssService.itHotEntry()
            CATEGORY.IT_ENTRY_LIST -> rssService.itEntryList()
            CATEGORY.ENTERTAINMENT_HOT_ENTRY -> rssService.entertainmentHotEntry()
            CATEGORY.ENTERTAINMENT_ENTRY_LIST -> rssService.entertainmentEntryList()
            CATEGORY.GAME_HOT_ENTRY -> rssService.gameHotEntry()
            CATEGORY.GAME_ENTRY_LIST -> rssService.gameEntryList()
            CATEGORY.FUN_HOT_ENTRY -> rssService.funHotEntry()
            CATEGORY.FUN_ENTRY_LIST -> rssService.funEntryList()
//            CATEGORY.VIDEO -> rssService.video()
            else -> throw IllegalArgumentException()
        }
    }
}

enum class CATEGORY {
    GENERAL_HOT_ENTRY,
    GENERAL_ENTRY_LIST,
    SOCIAL_HOT_ENTRY,
    SOCIAL_ENTRY_LIST,
    ECONOMICS_HOT_ENTRY,
    ECONOMICS_ENTRY_LIST,
    LIFE_HOT_ENTRY,
    LIFE_ENTRY_LIST,
    KNOWLEDGE_HOT_ENTRY,
    KNOWLEDGE_ENTRY_LIST,
    IT_HOT_ENTRY,
    IT_ENTRY_LIST,
    ENTERTAINMENT_HOT_ENTRY,
    ENTERTAINMENT_ENTRY_LIST,
    GAME_HOT_ENTRY,
    GAME_ENTRY_LIST,
    FUN_HOT_ENTRY,
    FUN_ENTRY_LIST,
    VIDEO
}