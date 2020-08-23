package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotDaysRepository
import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotTasksRepository
import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotsRepository
import one.gypsy.neatorganizer.domain.datasource.routines.reset.*
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAlarm
import org.koin.dsl.module

val routinesResetDataSourceModule = module {
    factory<RoutineSnapshotsDataSource> { UserRoutineSnapshotsDataSource(get()) }
    factory<RoutineSnapshotTasksDataSource> { UserRoutineSnapshotTasksDataSource(get()) }
    factory<RoutineSnapshotDaysDataSource> { UserRoutineSnapshotDaysDataSource(get()) }
}

val routinesResetRepositoryModule = module {
    factory { RoutineSnapshotsRepository(get()) }
    factory { RoutineSnapshotTasksRepository(get()) }
    factory { RoutineSnapshotDaysRepository(get()) }
}

val routinesResetUseCaseModule = module {
}

val routinesResetUtilsModule = module {
    factory { RoutinesResetAlarm() }
}
