package com.kyaracter.hatenabookmark.data.remote.service

import com.kyaracter.hatenabookmark.data.remote.entity.Rss
import io.reactivex.Single
import retrofit2.http.GET

interface RssService {

    @GET("hotentry.rss")
    fun generalHotEntry(): Single<Rss>

    @GET("entrylist.rss")
    fun generalEntryList(): Single<Rss>

    @GET("hotentry/social.rss")
    fun socialHotEntry(): Single<Rss>

    @GET("entrylist/social.rss")
    fun socialEntryList(): Single<Rss>

    @GET("hotentry/economics.rss")
    fun economicsHotEntry(): Single<Rss>

    @GET("entrylist/economics.rss")
    fun economicsEntryList(): Single<Rss>

    @GET("hotentry/life.rss")
    fun lifeHotEntry(): Single<Rss>

    @GET("entrylist/life.rss")
    fun lifeEntryList(): Single<Rss>

    @GET("hotentry/knowledge.rss")
    fun knowledgeHotEntry(): Single<Rss>

    @GET("entrylist/knowledge.rss")
    fun knowledgeEntryList(): Single<Rss>

    @GET("hotentry/it.rss")
    fun itHotEntry(): Single<Rss>

    @GET("entrylist/it.rss")
    fun itEntryList(): Single<Rss>

    @GET("hotentry/entertainment.rss")
    fun entertainmentHotEntry(): Single<Rss>

    @GET("entrylist/entertainment.rss")
    fun entertainmentEntryList(): Single<Rss>

    @GET("hotentry/game.rss")
    fun gameHotEntry(): Single<Rss>

    @GET("entrylist/game.rss")
    fun gameEntryList(): Single<Rss>

    @GET("hotentry/fun.rss")
    fun funHotEntry(): Single<Rss>

    @GET("entrylist/fun.rss")
    fun funEntryList(): Single<Rss>

//    @GET("video.rss")
//    fun video(): Single<Rss>
}