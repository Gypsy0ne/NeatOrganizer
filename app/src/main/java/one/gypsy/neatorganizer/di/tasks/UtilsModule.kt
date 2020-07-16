package one.gypsy.neatorganizer.di.tasks

import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import org.koin.dsl.module

val tasksUtilsModule = module {
    factory { TaskListMapper() }
}