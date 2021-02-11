package one.gypsy.neatorganizer.domain.datasource.notes

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

interface NotesDataSource {
    suspend fun insert(noteEntry: NoteEntry)
    suspend fun deleteById(noteId: Long)
    suspend fun update(note: Note)
    suspend fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>>
    suspend fun getNoteById(noteId: Long): LiveData<Note>
}
