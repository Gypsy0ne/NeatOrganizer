package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.RoutineTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.RoutinesDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.UserRoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.UserRoutineTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.UserRoutinesDataSource
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutineSchedule
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutineTask
import one.gypsy.neatorganizer.domain.interactors.routines.GetAllRoutines
import one.gypsy.neatorganizer.domain.interactors.routines.RemoveRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.RemoveRoutineById
import one.gypsy.neatorganizer.domain.interactors.routines.RemoveRoutineTask
import one.gypsy.neatorganizer.domain.interactors.routines.RunAllRoutinesSnapshotReset
import one.gypsy.neatorganizer.domain.interactors.routines.UpdateRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.UpdateRoutineSchedule
import one.gypsy.neatorganizer.domain.interactors.routines.UpdateRoutineTask
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineTaskViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RemoveRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
import one.gypsy.neatorganizer.repositories.routines.RoutineSchedulesRepository
import one.gypsy.neatorganizer.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.repositories.routines.RoutinesRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val routinesDataSourceModule = module {
    factory<RoutineSchedulesDataSource> { UserRoutineSchedulesDataSource(get()) }
    factory<RoutinesDataSource> { UserRoutinesDataSource(get()) }
    factory<RoutineTasksDataSource> { UserRoutineTasksDataSource(get()) }
}

val routinesRepositoryModule = module {
    factory { RoutineSchedulesRepository(get()) }
    factory { RoutinesRepository(get()) }
    factory { RoutineTasksRepository(get()) }
}

val routinesUseCaseModule = module {
    factory { AddRoutine(get()) }
    factory { AddRoutineSchedule(get()) }
    factory { AddRoutineTask(get()) }
    factory { GetAllRoutines(get()) }
    factory { RemoveRoutine(get()) }
    factory { RemoveRoutineById(get()) }
    factory { RemoveRoutineTask(get()) }
    factory { RunAllRoutinesSnapshotReset(get()) }
    factory { UpdateRoutine(get()) }
    factory { UpdateRoutineSchedule(get()) }
    factory { UpdateRoutineTask(get()) }
}

val routinesUtilsModule = module {
    factory { RoutineListMapper() }
}

val routinesViewModelModule = module {
    viewModel { (id: Long) -> AddRoutineTaskViewModel(get(), id) }
    viewModel { AddRoutineViewModel(addRoutineSchedule = get(), addRoutineUseCase = get()) }
    viewModel { RemoveRoutineViewModel(get()) }
    viewModel {
        RoutinesViewModel(
            getAllRoutinesUseCase = get(),
            updateRoutine = get(),
            removeRoutineTask = get(),
            updateRoutineTask = get(),
            updateRoutineSchedule = get(),
            routineListMapper = get()
        )
    }
}
