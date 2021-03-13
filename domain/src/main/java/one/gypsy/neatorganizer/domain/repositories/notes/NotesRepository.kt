package one.gypsy.neatorganizer.domain.repositories.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import one.gypsy.neatorganizer.data.datasource.notes.NotesDataSource
import one.gypsy.neatorganizer.domain.dto.notes.NoteDto
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntryDto
import one.gypsy.neatorganizer.domain.dto.notes.toDto
import one.gypsy.neatorganizer.domain.dto.notes.toNote
import one.gypsy.neatorganizer.domain.dto.notes.toNoteEntry

class NotesRepository(private val dataSource: NotesDataSource) {

    suspend fun insertNoteEntry(noteEntry: NoteEntryDto) =
        dataSource.insert(noteEntry.toNoteEntry())

    suspend fun deleteNoteById(noteId: Long) = dataSource.deleteById(noteId)

    suspend fun updateNote(note: NoteDto) = dataSource.update(note.toNote())

    suspend fun getAllNoteEntriesObservable(): LiveData<List<NoteEntryDto>> =
        dataSource.getAllNoteEntriesObservable().map { entries ->
            entries.map { it.toDto() }
        }

    suspend fun getNoteById(noteId: Long): LiveData<NoteDto> = dataSource.getNoteById(noteId).map {
        it.toDto()
    }
}
