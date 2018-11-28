package com.baz.movie.movies.upcoming.module

import androidx.lifecycle.ViewModel
import com.baz.movie.app.ViewModelKey
import com.baz.movie.movies.popular.PopularViewModel
import com.baz.movie.movies.upcoming.UpcomingFragment
import com.baz.movie.movies.upcoming.UpcomingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [UpcomingModule::class])
internal abstract class UpcomingBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun upcomingFragment(): UpcomingFragment

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    internal abstract fun bindsUpcomingViewModel(viewModel: UpcomingViewModel): ViewModel
}