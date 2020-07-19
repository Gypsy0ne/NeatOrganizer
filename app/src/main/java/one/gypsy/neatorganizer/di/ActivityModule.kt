package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import one.gypsy.neatorganizer.presentation.HomeActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

}