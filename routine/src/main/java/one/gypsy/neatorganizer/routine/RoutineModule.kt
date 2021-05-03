package one.gypsy.neatorganizer.routine

import one.gypsy.neatorganizer.routine.alarm.RoutinesResetManager
import one.gypsy.neatorganizer.routine.model.RoutineListMapper
import one.gypsy.neatorganizer.routine.vm.AddRoutineTaskViewModel
import one.gypsy.neatorganizer.routine.vm.AddRoutineViewModel
import one.gypsy.neatorganizer.routine.vm.RemoveRoutineViewModel
import one.gypsy.neatorganizer.routine.vm.RoutinesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

// val routineModule = module {
//    routineViewModelModule
//    routineUtilsModule
// }

val routineUtilsModule = module {
    factory { RoutineListMapper() }
    factory {
        RoutinesResetManager(
            resetRoutineDaysUseCase = get(),
            addSnapshotUseCase = get(),
            getLastSnapshotUseCase = get()
        )
    }
}

val routineViewModelModule = module {
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
