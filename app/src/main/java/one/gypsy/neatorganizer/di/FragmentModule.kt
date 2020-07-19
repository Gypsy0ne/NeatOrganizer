package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import one.gypsy.neatorganizer.presentation.people.view.AddPersonDialogFragment
import one.gypsy.neatorganizer.presentation.people.view.PeopleFragment
import one.gypsy.neatorganizer.presentation.people.view.RateInteractionDialogFragment
import one.gypsy.neatorganizer.presentation.profile.PersonProfileFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleFragment(): PeopleFragment

    @ContributesAndroidInjector
    abstract fun contributeAddPersonFragment(): AddPersonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributePersonProfileFragment(): PersonProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeRateInteractionFragment(): RateInteractionDialogFragment

}