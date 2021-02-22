package one.gypsy.neatorganizer.data

import one.gypsy.neatorganizer.data.datasource.notes.NoteWidgetsDataSource
import one.gypsy.neatorganizer.data.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.data.datasource.notes.UserNoteWidgetsDataSource
import one.gypsy.neatorganizer.data.datasource.notes.UserNotesDataSource
import one.gypsy.neatorganizer.data.datasource.routines.RoutineSchedulesDataSource
import one.gypsy.neatorganizer.data.datasource.routines.RoutineTasksDataSource
import one.gypsy.neatorganizer.data.datasource.routines.RoutinesDataSource
import one.gypsy.neatorganizer.data.datasource.routines.UserRoutineSchedulesDataSource
import one.gypsy.neatorganizer.data.datasource.routines.UserRoutineTasksDataSource
import one.gypsy.neatorganizer.data.datasource.routines.UserRoutinesDataSource
import one.gypsy.neatorganizer.data.datasource.routines.reset.RoutineSnapshotsDataSource
import one.gypsy.neatorganizer.data.datasource.routines.reset.UserRoutineSnapshotsDataSource
import one.gypsy.neatorganizer.data.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.data.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.data.datasource.tasks.TaskWidgetDataSource
import one.gypsy.neatorganizer.data.datasource.tasks.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.data.datasource.tasks.UserSingleTasksDataSource
import one.gypsy.neatorganizer.data.datasource.tasks.UserTaskWidgetDataSource
import org.koin.dsl.module

val dataModule = module {
    notesDataSourceModule
    routinesDataSourceModule
    routinesResetDataSourceModule
    tasksDataSourceModule
}

private val notesDataSourceModule = module {
    factory<NotesDataSource> { UserNotesDataSource(get()) }
    factory<NoteWidgetsDataSource> { UserNoteWidgetsDataSource(get()) }
}

private val routinesDataSourceModule = module {
    factory<RoutineSchedulesDataSource> { UserRoutineSchedulesDataSource(get()) }
    factory<RoutinesDataSource> { UserRoutinesDataSource(get()) }
    factory<RoutineTasksDataSource> { UserRoutineTasksDataSource(get()) }
}

private val routinesResetDataSourceModule = module {
    factory<RoutineSnapshotsDataSource> { UserRoutineSnapshotsDataSource(get()) }
}

private val tasksDataSourceModule = module {
    factory<SingleTasksDataSource> { UserSingleTasksDataSource(get()) }
    factory<SingleTaskGroupsDataSource> { UserSingleTaskGroupsDataSource(get()) }
    factory<TaskWidgetDataSource> { UserTaskWidgetDataSource(get()) }
}
