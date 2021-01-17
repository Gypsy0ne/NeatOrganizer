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
import one.gypsy.neatorganizer.domain.interactors.notes.widget.SaveNoteWidget
import one.gypsy.neatorganizer.presentation.notes.vm.AddNoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.DeleteNoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetConfigurationViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NotesListingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
}
