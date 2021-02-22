package one.gypsy.neatorganizer.data.datasource.notes

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.data.model.notes.Note
import one.gypsy.neatorganizer.data.model.notes.NoteEntry

interface NotesDataSource {
    suspend fun insert(noteEntry: NoteEntry)
    suspend fun deleteById(noteId: Long)
    suspend fun update(note: Note)
    suspend fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>>
    suspend fun getNoteById(noteId: Long): LiveData<Note>
}
