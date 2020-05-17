package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper

@Module
class UtilsModule {

    @Provides
    fun provideTaskListMapper(): TaskListMapper = TaskListMapper()

    @Provides
    fun provideRoutineListMapper(): RoutineListMapper = RoutineListMapper()
}