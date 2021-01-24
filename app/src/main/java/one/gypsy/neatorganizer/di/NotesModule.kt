package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.data.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.data.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.datasource.notes.NoteWidgetsDataSource
import one.gypsy.neatorganizer.domain.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.domain.datasource.notes.UserNoteWidgetsDataSource
import one.gypsy.neatorganizer.domain.datasource.notes.UserNotesDataSource
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
import one.gypsy.neatorganizer.presentation.common.WidgetNotifier
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.notes.view.widget.management.NoteWidgetNotifier
import one.gypsy.neatorganizer.presentation.notes.view.widget.remote.NoteWidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.notes.vm.AddNoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.DeleteNoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetConfigurationViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetContentManageViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetSelectionViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NotesListingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// TODO extract modules and then split di files
// introduce scopes for util classes
// pack many modules into one
val notesDataSourceModule = module {
    factory<NotesDataSource> { UserNotesDataSource(get()) }
    factory<NoteWidgetsDataSource> { UserNoteWidgetsDataSource(get()) }
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
val notesUtilsModule = module {
    factory<WidgetRemoteViewManager>(named("noteRemoteViewManager")) {
        NoteWidgetRemoteViewManager(
            get(),
            get(),
            get(),
            get()
        )
    }
    factory<WidgetNotifier>(named("noteWidgetNotifier")) { NoteWidgetNotifier(androidContext()) }
}

val notesViewModelModule = module {
    viewModel { (id: Long) ->
        NoteViewModel(
            id,
            getNoteByIdUseCase = get(),
            updateNoteUseCase = get()
        )
    }
    viewModel { (id: Long) ->
        NoteWidgetViewModel()
    }
    viewModel { AddNoteViewModel(insertNoteEntryUseCase = get()) }
    viewModel {
        NotesListingViewModel(
            getAllNoteEntriesUseCase = get()
        )
    }
    viewModel {
        DeleteNoteViewModel(
            deleteNoteByIdUseCase = get(),
        )
    }
    viewModel {
        NoteWidgetConfigurationViewModel(
            getAllNoteEntriesUseCase = get(),
            saveNoteWidgetUseCase = get()

        )
    }
    viewModel { (id: Long) ->
        NoteWidgetContentManageViewModel(
            noteId = id,
            updateNoteUseCase = get(),
            getNoteByIdUseCase = get()
        )
    }
    viewModel { (widgetId: Int) ->
        NoteWidgetSelectionViewModel(
            getAllNoteEntriesUseCase = get(),
            widgetId = widgetId,
            updateNoteWidgetUseCase = get()
        )
    }
}
