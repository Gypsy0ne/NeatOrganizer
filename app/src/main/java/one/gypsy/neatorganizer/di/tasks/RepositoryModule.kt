package one.gypsy.neatorganizer.di.tasks

import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import org.koin.dsl.module

val tasksRepositoryModule = module {
    factory { SingleTasksRepository(get()) }
    factory { SingleTaskGroupsRepository(get()) }
}