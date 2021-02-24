package one.gypsy.neatorganizer.note

import one.gypsy.neatorganizer.core.widget.WidgetNotifier
import one.gypsy.neatorganizer.core.widget.WidgetRemoteViewManager
import one.gypsy.neatorganizer.note.view.widget.management.NoteWidgetNotifier
import one.gypsy.neatorganizer.note.view.widget.remote.NoteWidgetRemoteViewManager
import one.gypsy.neatorganizer.note.vm.AddNoteViewModel
import one.gypsy.neatorganizer.note.vm.DeleteNoteViewModel
import one.gypsy.neatorganizer.note.vm.NoteViewModel
import one.gypsy.neatorganizer.note.vm.NoteWidgetConfigurationViewModel
import one.gypsy.neatorganizer.note.vm.NoteWidgetContentManageViewModel
import one.gypsy.neatorganizer.note.vm.NoteWidgetSelectionViewModel
import one.gypsy.neatorganizer.note.vm.NotesListingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val noteModule = module {
    noteUtilsModule
    noteViewModelModule
}

private val noteUtilsModule = module {
    factory<WidgetRemoteViewManager>(named("noteRemoteViewManager")) {
        NoteWidgetRemoteViewManager(
            context = get(),
            widgetManager = get(),
            loadTitledNoteWidgetUseCase = get(),
            deleteNoteWidgetUseCase = get()
        )
    }
    factory<WidgetNotifier>(named("noteWidgetNotifier")) {
        NoteWidgetNotifier(
            androidContext()
        )
    }
}

private val noteViewModelModule = module {
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
