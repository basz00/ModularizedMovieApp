package com.baz.movie.app

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilderModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: CustomViewModelFactory):
            ViewModelProvider.Factory
}