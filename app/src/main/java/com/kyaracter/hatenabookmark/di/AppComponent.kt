package com.kyaracter.hatenabookmark.di

import android.app.Application
import com.kyaracter.hatenabookmark.App
import com.kyaracter.hatenabookmark.util.HatenaAppGlideModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class, DisposableModule::class, SchedulerModule::class, PersistentModule::class, RemoteModule::class])
interface AppComponent : AndroidInjector<App> {

    fun inject(glideModule: HatenaAppGlideModule)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}