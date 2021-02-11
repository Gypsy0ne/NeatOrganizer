package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetManager
import org.koin.dsl.module

val routinesResetRepositoryModule = module {
    factory { RoutineSnapshotsRepository(get()) }
}

val routinesResetUtilsModule = module {
    factory {
        one.gypsy.neatorganizer.domain.routines.reset.RoutinesResetSnapshooter(
            get(),
            get(),
            get()
        )
    }
    factory { RoutinesResetManager(get()) }
}
