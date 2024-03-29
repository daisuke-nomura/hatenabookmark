package com.kyaracter.hatenabookmark.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.kyaracter.hatenabookmark.App
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Inject


@GlideModule
class HatenaAppGlideModule : AppGlideModule() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        (context.applicationContext as App).appComponent.inject(this)
        super.registerComponents(context, glide, registry)
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
    }
}
