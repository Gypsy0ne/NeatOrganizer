package one.gypsy.neatorganizer.domain.repositories.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.domain.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntryDto
import one.gypsy.neatorganizer.domain.dto.notes.toNoteEntry
import one.gypsy.neatorganizer.domain.model.notes.Note

class NotesRepository(private val dataSource: NotesDataSource) {

    suspend fun insertNoteEntry(noteEntry: NoteEntryDto) =
        dataSource.insert(noteEntry.toNoteEntry())

    suspend fun deleteNoteById(noteId: Long) = dataSource.deleteById(noteId)

    suspend fun updateNote(note: Note) = dataSource.update(note)

    suspend fun getAllNoteEntriesObservable(): LiveData<List<NoteEntryDto>> =
        Transformations.map(dataSource.getAllNoteEntriesObservable()) { entries ->
            entries.map { it.toDto() }
        }

    suspend fun getNoteById(noteId: Long): LiveData<Note> = dataSource.getNoteById(noteId)
}
