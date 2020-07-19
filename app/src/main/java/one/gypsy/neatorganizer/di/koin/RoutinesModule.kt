package one.gypsy.neatorganizer.di.koin

import one.gypsy.neatorganizer.data.repositories.routines.RoutineSchedulesRepository
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.datasource.routines.*
import one.gypsy.neatorganizer.domain.interactors.routines.*
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineTaskViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RemoveRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
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
    factory { ResetAllRoutineTasks(get()) }
    factory { UpdateRoutine(get()) }
    factory { UpdateRoutineSchedule(get()) }
    factory { UpdateRoutineTask(get()) }
}

val routinesUtilsModule = module {
    factory { RoutineListMapper() }
    /*
    //    @Provides
//    fun provideRoutinesResetAutoStart(): RoutinesResetAutoStart = RoutinesResetAutoStart()

    @Provides
    fun contributeRoutinesResetAlarm(): RoutinesResetAlarm = RoutinesResetAlarm()
     */
}

val routinesViewModelModule = module {
    viewModel { (id: Long) -> AddRoutineTaskViewModel(get(), id) }
    viewModel { AddRoutineViewModel(addRoutineSchedule = get(), addRoutineUseCase = get()) }
    viewModel { RemoveRoutineViewModel(get()) }
    viewModel {
        RoutinesViewModel(
            getAllRoutinesUseCase = get(),
            removeRoutine = get(),
            updateRoutine = get(),
            removeRoutineTask = get(),
            updateRoutineTask = get(),
            updateRoutineSchedule = get(),
            routineListMapper = get()
        )
    }
}