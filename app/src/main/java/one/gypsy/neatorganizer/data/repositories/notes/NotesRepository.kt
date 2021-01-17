package one.gypsy.neatorganizer.data.repositories.notes

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

class NotesRepository(private val dataSource: NotesDataSource) {

    suspend fun insertNoteEntry(noteEntry: NoteEntry) = dataSource.insert(noteEntry)

    suspend fun deleteNoteById(noteId: Long) = dataSource.deleteById(noteId)

    suspend fun updateNote(note: Note) = dataSource.update(note)

    suspend fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>> =
        dataSource.getAllNoteEntriesObservable()

    suspend fun getNoteById(noteId: Long): LiveData<Note> = dataSource.getNoteById(noteId)
}
