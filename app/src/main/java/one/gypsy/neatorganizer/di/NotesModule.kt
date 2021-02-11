package one.gypsy.neatorganizer.di

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
import one.gypsy.neatorganizer.presentation.notes.vm.NotesListingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val notesUtilsModule = module {
    factory<WidgetRemoteViewManager>(named("noteRemoteViewManager")) {
        NoteWidgetRemoteViewManager(
            context = get(),
            widgetManager = get(),
            loadTitledNoteWidgetUseCase = get(),
            deleteNoteWidgetUseCase = get()
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
