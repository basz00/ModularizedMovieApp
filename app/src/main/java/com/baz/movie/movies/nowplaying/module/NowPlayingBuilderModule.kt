package com.baz.movie.movies.nowplaying.module

import androidx.lifecycle.ViewModel
import com.baz.movie.app.ViewModelKey
import com.baz.movie.movies.nowplaying.NowPlayingFragment
import com.baz.movie.movies.nowplaying.NowPlayingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [NowPlayingModule::class])
internal abstract class NowPlayingBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun nowPlayingFragment(): NowPlayingFragment

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    internal abstract fun bindsNowPlayingViewModel(viewModel: NowPlayingViewModel): ViewModel
}