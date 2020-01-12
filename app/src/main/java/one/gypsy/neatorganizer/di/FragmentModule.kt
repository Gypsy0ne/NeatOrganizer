package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import one.gypsy.neatorganizer.screens.people.view.AddPersonDialogFragment
import one.gypsy.neatorganizer.screens.people.view.PeopleFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleFragment(): PeopleFragment

    @ContributesAndroidInjector
    abstract fun contributeAddPersonPeopleFragment(): AddPersonDialogFragment
}