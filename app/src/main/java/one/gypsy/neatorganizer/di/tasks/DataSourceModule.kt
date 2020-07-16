package one.gypsy.neatorganizer.di.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTasksDataSource
import org.koin.dsl.module

val tasksDataSourceModule = module {
    factory<SingleTasksDataSource> { UserSingleTasksDataSource(get()) }
    factory<SingleTaskGroupsDataSource> { UserSingleTaskGroupsDataSource(get()) }
}