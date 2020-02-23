package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import one.gypsy.neatorganizer.presentation.people.view.AddPersonDialogFragment
import one.gypsy.neatorganizer.presentation.people.view.PeopleFragment
import one.gypsy.neatorganizer.presentation.profile.PersonProfileFragment
import one.gypsy.neatorganizer.presentation.tasks.view.AddTaskDialogFragment
import one.gypsy.neatorganizer.presentation.tasks.view.AddTaskGroupDialogFragment
import one.gypsy.neatorganizer.presentation.tasks.view.TasksFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePeopleFragment(): PeopleFragment

    @ContributesAndroidInjector
    abstract fun contributeAddPersonFragment(): AddPersonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributePersonProfileFragment(): PersonProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeAddTaskFragment(): AddTaskDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeAddTaskGroupFragment(): AddTaskGroupDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeTasksFragment(): TasksFragment
}