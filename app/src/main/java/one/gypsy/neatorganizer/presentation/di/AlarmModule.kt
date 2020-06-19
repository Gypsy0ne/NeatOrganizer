package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAlarm
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAutoStart

@Module
class AlarmModule {

    @Provides
    fun provideRoutinesResetAutoStart(): RoutinesResetAutoStart {
        return RoutinesResetAutoStart()
    }

    @Provides
    fun providesRoutinesResetAlarm(): RoutinesResetAlarm = RoutinesResetAlarm()
}