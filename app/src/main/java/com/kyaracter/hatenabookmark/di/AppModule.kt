package com.kyaracter.hatenabookmark.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindApplication(application: Application): Context
}