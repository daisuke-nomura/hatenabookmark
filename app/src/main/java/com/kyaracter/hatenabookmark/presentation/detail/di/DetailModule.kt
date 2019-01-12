package com.kyaracter.hatenabookmark.presentation.detail.di

import androidx.lifecycle.ViewModel
import com.kyaracter.hatenabookmark.di.annotation.ViewModelKey
import com.kyaracter.hatenabookmark.presentation.detail.DetailFragment
import com.kyaracter.hatenabookmark.presentation.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun detailFragment(): DetailFragment
}