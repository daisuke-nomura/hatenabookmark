package com.kyaracter.hatenabookmark.presentation.main

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.Relay
import com.kyaracter.hatenabookmark.data.repository.CATEGORY
import com.kyaracter.hatenabookmark.data.usecase.RssUseCase
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import io.reactivex.Completable
import jp.keita.kagurazaka.rxproperty.RxProperty
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val rssUseCase: RssUseCase
) : ViewModel() {

    val progress = RxProperty(false)

    val nodata = RxProperty(false)

    val error = RxProperty(false)

    fun fetch(relay: Relay<List<RssItem>>, category: CATEGORY): Completable {
        return rssUseCase
            .fetch(relay, category)
            .doOnSubscribe {
                progress.set(true)
                nodata.set(false)
                error.set(false)
            }
            .doOnSuccess {
                if (it == 0) {
                    nodata.set(true)
                }
            }
            .doOnError {
                error.set(true)
            }
            .doFinally {
                progress.set(false)
            }
            .ignoreElement()
    }
}
