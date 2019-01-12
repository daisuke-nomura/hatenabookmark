package com.kyaracter.hatenabookmark.di

import androidx.lifecycle.ViewModelProvider
import com.kyaracter.hatenabookmark.presentation.detail.DetailActivity
import com.kyaracter.hatenabookmark.presentation.detail.di.DetailModule
import com.kyaracter.hatenabookmark.presentation.main.MainActivity
import com.kyaracter.hatenabookmark.presentation.main.di.MainModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun detailActivity(): DetailActivity
}