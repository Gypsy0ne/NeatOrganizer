package one.gypsy.neatorganizer.domain

import one.gypsy.neatorganizer.domain.interactors.notes.DeleteNoteById
import one.gypsy.neatorganizer.domain.interactors.notes.GetAllNoteEntries
import one.gypsy.neatorganizer.domain.interactors.notes.GetNoteById
import one.gypsy.neatorganizer.domain.interactors.notes.InsertNoteEntry
import one.gypsy.neatorganizer.domain.interactors.notes.UpdateNote
import one.gypsy.neatorganizer.domain.interactors.notes.widget.DeleteNoteWidgetById
import one.gypsy.neatorganizer.domain.interactors.notes.widget.GetAllNoteWidgetIds
import one.gypsy.neatorganizer.domain.interactors.notes.widget.GetAllNoteWidgets
import one.gypsy.neatorganizer.domain.interactors.notes.widget.LoadTitledNoteWidget
import one.gypsy.neatorganizer.domain.interactors.notes.widget.SaveNoteWidget
import one.gypsy.neatorganizer.domain.interactors.notes.widget.UpdateWidgetNote
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
import one.gypsy.neatorganizer.domain.interactors.tasks.AddSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.AddTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.CreateTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.DeleteTaskWidgetById
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroups
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupId
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupIdObservable
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllTaskWidgetIds
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllTaskWidgets
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupWithTasksById
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTaskGroupIdByWidgetId
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTitledTaskWidgetByIdObservable
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTitledTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateTaskWidgetLinkedGroup
import one.gypsy.neatorganizer.domain.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.domain.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineSchedulesRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository
import org.koin.dsl.module

val domainModule = module {
    notesRepositoryModule
    notesUseCaseModule
    routinesUseCaseModule
    routinesRepositoryModule
    tasksUseCaseModule
    tasksRepositoryModule
}

private val tasksRepositoryModule = module {
    factory { SingleTasksRepository(get()) }
    factory { SingleTaskGroupsRepository(get()) }
    factory { TaskWidgetsRepository(get()) }
}

private val tasksUseCaseModule = module {
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

private val routinesRepositoryModule = module {
    factory { RoutineSchedulesRepository(get()) }
    factory { RoutinesRepository(get()) }
    factory { RoutineTasksRepository(get()) }
}

private val routinesUseCaseModule = module {
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

private val notesRepositoryModule = module {
    factory { NotesRepository(get()) }
    factory { NoteWidgetsRepository(get()) }
}

private val notesUseCaseModule = module {
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
