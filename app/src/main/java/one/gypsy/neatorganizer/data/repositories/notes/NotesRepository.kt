package one.gypsy.neatorganizer.data.repositories.notes

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

class NotesRepository(private val dataSource: NotesDataSource) {

    fun insertNote(note: Note) = dataSource.insert(note)

    fun removeNote(note: Note) = dataSource.remove(note)

    fun updateNote(note: Note) = dataSource.update(note)

    fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>> =
        dataSource.getAllNoteEntriesObservable()

    fun getNoteById(noteId: Long): LiveData<Note> = dataSource.getNoteById(noteId)
}
