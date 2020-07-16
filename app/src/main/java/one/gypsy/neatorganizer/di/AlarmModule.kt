package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAutoStart

@Module
abstract class AlarmModule {
    @ContributesAndroidInjector
    abstract fun contributeRoutinesResetAlarm(): RoutinesResetAutoStart

}