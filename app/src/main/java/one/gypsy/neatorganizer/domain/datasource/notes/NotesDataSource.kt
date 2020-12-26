package one.gypsy.neatorganizer.domain.datasource.notes

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

interface NotesDataSource {
    fun insert(note: Note): Long
    fun remove(note: Note)
    fun update(note: Note)
    fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>>
    fun getNoteById(noteId: Long): LiveData<Note>
}
