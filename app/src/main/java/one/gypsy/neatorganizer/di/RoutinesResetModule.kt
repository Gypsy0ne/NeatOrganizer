package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotsRepository
import one.gypsy.neatorganizer.domain.datasource.routines.reset.RoutineSnapshotsDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.reset.UserRoutineSnapshotsDataSource
import one.gypsy.neatorganizer.domain.interactors.routines.reset.RoutinesResetSnapshooter
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetManager
import org.koin.dsl.module

val routinesResetDataSourceModule = module {
    factory<RoutineSnapshotsDataSource> { UserRoutineSnapshotsDataSource(get()) }
}

val routinesResetRepositoryModule = module {
    factory { RoutineSnapshotsRepository(get()) }
}

val routinesResetUtilsModule = module {
    factory { RoutinesResetSnapshooter(get(), get(), get()) }
    factory { RoutinesResetManager(get()) }
}
