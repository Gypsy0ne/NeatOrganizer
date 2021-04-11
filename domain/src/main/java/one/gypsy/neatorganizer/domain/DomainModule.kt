package one.gypsy.neatorganizer.domain

import one.gypsy.neatorganizer.domain.interactors.notes.*
import one.gypsy.neatorganizer.domain.interactors.notes.widget.*
import one.gypsy.neatorganizer.domain.interactors.routines.*
import one.gypsy.neatorganizer.domain.interactors.routines.reset.RoutinesResetSnapshooter
import one.gypsy.neatorganizer.domain.interactors.tasks.*
import one.gypsy.neatorganizer.domain.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.domain.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineSchedulesRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.repositories.routines.reset.RoutineSnapshotsRepository
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository
import org.koin.dsl.module

val routinesResetRepositoryModule = module {
    factory { RoutineSnapshotsRepository(get()) }
}

val routinesResetUtilsModule = module {
    factory {
        RoutinesResetSnapshooter(
            routinesRepository = get(),
            routineSnapshotsRepository = get(),
            routineTasksRepository = get()
        )
    }
}

val tasksRepositoryModule = module {
    factory { SingleTasksRepository(get()) }
    factory { SingleTaskGroupsRepository(get()) }
    factory { TaskWidgetsRepository(get()) }
}

val tasksUseCaseModule = module {
    factory { AddSingleTask(get()) }
    factory { AddTaskGroup(get()) }
    factory { GetAllSingleTaskGroups(get()) }
    factory { RemoveSingleTask(get()) }
    factory { RemoveTaskGroup(get()) }
    factory { RemoveTaskGroupById(get()) }
    factory { UpdateSingleTask(get()) }
    factory { UpdateSingleTaskGroupWithTasks(get()) }
    factory { GetAllSingleTaskGroupEntries(get()) }
    factory { CreateTaskWidget(get()) }
    factory { LoadTitledTaskWidget(get()) }
    factory { GetAllSingleTasksByGroupId(get()) }
    factory { GetSingleTaskGroupWithTasksById(get()) }
    factory { GetSingleTaskGroupById(get()) }
    factory { GetAllSingleTasksByGroupIdObservable(get()) }
    factory { UpdateSingleTaskGroup(get()) }
    factory { GetAllTaskWidgetIds(get()) }
    factory { DeleteTaskWidgetById(get()) }
    factory { UpdateTaskWidgetLinkedGroup(get()) }
    factory { GetAllTaskWidgets(get()) }
    factory { GetTitledTaskWidgetByIdObservable(get()) }
    factory { GetTaskGroupIdByWidgetId(get()) }
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

val notesRepositoryModule = module {
    factory { NotesRepository(get()) }
    factory { NoteWidgetsRepository(get()) }
}

val notesUseCaseModule = module {
    factory { DeleteNoteById(get()) }
    factory { GetAllNoteEntries(get()) }
    factory { GetNoteById(get()) }
    factory { InsertNoteEntry(get()) }
    factory { UpdateNote(get()) }
    factory { SaveNoteWidget(get()) }
    factory { DeleteNoteWidgetById(get()) }
    factory { LoadTitledNoteWidget(get()) }
    factory { UpdateWidgetNote(get()) }
    factory { GetAllNoteWidgetIds(get()) }
    factory { GetAllNoteWidgets(get()) }
}
