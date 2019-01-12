package com.kyaracter.hatenabookmark

import com.kyaracter.hatenabookmark.di.AppComponent
import com.kyaracter.hatenabookmark.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}