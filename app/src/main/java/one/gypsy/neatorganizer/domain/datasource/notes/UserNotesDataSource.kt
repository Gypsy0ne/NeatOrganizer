package one.gypsy.neatorganizer.domain.datasource.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.notes.NotesDao
import one.gypsy.neatorganizer.data.database.entity.notes.toNote
import one.gypsy.neatorganizer.data.database.entity.notes.toNoteEntry
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.dto.notes.toNoteEntity

class UserNotesDataSource(private val notesDao: NotesDao) : NotesDataSource {

    override fun insert(noteEntry: NoteEntry) = notesDao.insert(noteEntry.toNoteEntity())

    override fun deleteById(noteId: Long) = notesDao.deleteNoteById(noteId)

    override fun update(note: Note) = notesDao.update(note.toNoteEntity())

    override fun getAllNoteEntriesObservable(): LiveData<List<NoteEntry>> =
        Transformations.map(notesDao.getAllNotesObservable()) {
            it.map { entity -> entity.toNoteEntry() }
        }

    override fun getNoteById(noteId: Long): LiveData<Note> =
        Transformations.map(notesDao.getNoteByIdObservable(noteId)) {
            it.toNote()
        }
}
