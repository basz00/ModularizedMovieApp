package com.baz.movie.main

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}