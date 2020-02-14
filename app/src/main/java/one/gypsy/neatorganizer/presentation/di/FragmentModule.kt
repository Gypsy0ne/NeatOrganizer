package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import one.gypsy.neatorganizer.presentation.people.view.AddPersonDialogFragment
import one.gypsy.neatorganizer.presentation.people.view.PeopleFragment
import one.gypsy.neatorganizer.presentation.profile.PersonProfileFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleFragment(): PeopleFragment

    @ContributesAndroidInjector
    abstract fun contributeAddPersonPeopleFragment(): AddPersonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributePersonProfileFragment(): PersonProfileFragment
}