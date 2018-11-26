package com.baz.movie.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(
        factory: ViewModelProvider.Factory? = null): T {
    factory?.let {
        return ViewModelProviders.of(this, factory)[T::class.java]
    }
    return ViewModelProviders.of(this)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.getViewModel(
        factory: ViewModelProvider.Factory? = null): T {
    factory?.let {
        return ViewModelProviders.of(this, factory)[T::class.java]
    }
    return ViewModelProviders.of(this)[T::class.java]
}