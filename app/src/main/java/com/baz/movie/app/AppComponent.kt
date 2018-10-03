package com.baz.movie.app

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
internal interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}