package one.gypsy.neatorganizer.di.tasks

import one.gypsy.neatorganizer.domain.interactors.tasks.*
import org.koin.dsl.module

val tasksUseCaseModule = module {
    factory { AddSingleTask(get()) }
    factory { AddTaskGroup(get()) }
    factory { GetAllSingleTaskGroups(get()) }
    factory { RemoveSingleTask(get()) }
    factory { RemoveTaskGroup(get()) }
    factory { RemoveTaskGroupById(get()) }
    factory { UpdateSingleTask(get()) }
    factory { UpdateTaskGroup(get()) }
}