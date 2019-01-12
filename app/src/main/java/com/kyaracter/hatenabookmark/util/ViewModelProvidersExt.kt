package com.kyaracter.hatenabookmark.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


fun FragmentActivity.viewModelOf(factory: ViewModelProvider.Factory): ViewModelProvider {
    return ViewModelProviders.of(this, factory)
}

fun Fragment.viewModelOf(factory: ViewModelProvider.Factory): ViewModelProvider {
    return ViewModelProviders.of(requireActivity(), factory)
}