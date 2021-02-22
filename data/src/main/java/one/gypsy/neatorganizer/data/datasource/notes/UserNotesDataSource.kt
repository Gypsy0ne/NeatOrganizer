package one.gypsy.neatorganizer.data.datasource.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.model.notes.Note
import one.gypsy.neatorganizer.data.model.notes.NoteEntry
import one.gypsy.neatorganizer.data.model.notes.toNote
import one.gypsy.neatorganizer.data.model.notes.toNoteEntity
import one.gypsy.neatorganizer.data.model.notes.toNoteEntry
import one.gypsy.neatorganizer.database.dao.notes.NotesDao

class UserNotesDataSource(private val notesDao: NotesDao) : NotesDataSource {

    override suspend fun insert(noteEntry: NoteEntry) = notesDao.insert(noteEntry.toNoteEntity())

    override suspend fun deleteById(noteId: Long) = notesDao.deleteNoteById(noteId)

    override suspend fun update(note: Note) = notesDao.update(note.toNoteEntity())

    override suspend fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>> =
        Transformations.map(notesDao.getAllNotesObservable()) {
            it.map { entity -> entity.toNoteEntry() }
        }

    @Suppress("USELESS_ELVIS")
    override suspend fun getNoteById(noteId: Long): LiveData<Note> {
        notesDao.getNoteById(noteId) ?: throw NullPointerException()
        return Transformations.map(notesDao.getNoteByIdObservable(noteId)) {
            it.toNote()
        }
    }
}
