package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineTaskViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RemoveRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
