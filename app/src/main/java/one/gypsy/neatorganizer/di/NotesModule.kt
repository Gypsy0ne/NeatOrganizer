package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.data.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.domain.datasource.notes.UserNotesDataSource
import one.gypsy.neatorganizer.domain.interactors.notes.*
import one.gypsy.neatorganizer.presentation.notes.vm.AddNoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NotesListingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesDataSourceModule = module {
    factory<NotesDataSource> { UserNotesDataSource(get()) }
}

val notesRepositoryModule = module {
    factory { NotesRepository(get()) }
}

val notesUseCaseModule = module {
    factory { DeleteNoteById(get()) }
    factory { GetAllNoteEntries(get()) }
    factory { GetNoteById(get()) }
    factory { InsertNoteEntry(get()) }
    factory { UpdateNote(get()) }
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
            deleteNoteByIdUseCase = get(),
            getAllNoteEntriesUseCase = get()
        )
    }
}
