package com.kyaracter.hatenabookmark.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
internal class DisposableModule {

    @Provides
    fun provideDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}