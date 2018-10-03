package com.baz.movie.app

import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

internal class Application: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}