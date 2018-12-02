package com.baz.movie.movies.details.module

import androidx.lifecycle.ViewModel
import com.baz.movie.app.ViewModelKey
import com.baz.movie.movies.details.DetailsActivity
import com.baz.movie.movies.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [DetailsModule::class])
internal abstract class DetailsBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun detailsFragment(): DetailsActivity

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun bindsDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}