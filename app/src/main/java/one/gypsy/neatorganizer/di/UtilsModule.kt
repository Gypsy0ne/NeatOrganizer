package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAlarm
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper

@Module
class UtilsModule {

    @Provides
    fun provideTaskListMapper(): TaskListMapper = TaskListMapper()

    @Provides
    fun provideRoutineListMapper(): RoutineListMapper = RoutineListMapper()

//    @Provides
//    fun provideRoutinesResetAutoStart(): RoutinesResetAutoStart = RoutinesResetAutoStart()

    @Provides
    fun contributeRoutinesResetAlarm(): RoutinesResetAlarm = RoutinesResetAlarm()

}