package one.gypsy.neatorganizer.data.database.dao.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.notes.NoteEntity

@Dao
interface NotesDao : BaseDao<NoteEntity> {

    @Query("SELECT * FROM notes")
    fun getAllNotesObservable(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteByIdObservable(noteId: Long): LiveData<NoteEntity>
}
