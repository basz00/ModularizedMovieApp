package com.baz.movie.movies.popular.module

import androidx.lifecycle.ViewModel
import com.baz.movie.app.ViewModelKey
import com.baz.movie.movies.popular.PopularFragment
import com.baz.movie.movies.popular.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [PopularModule::class])
internal abstract class PopularBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun popularFragment(): PopularFragment

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    internal abstract fun bindsPopularViewModel(viewModel: PopularViewModel): ViewModel
}