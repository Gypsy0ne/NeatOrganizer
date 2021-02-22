package one.gypsy.neatorganizer.data.datasource.notes

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.data.model.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.data.model.notes.TitledNoteWidgetEntry

interface NoteWidgetsDataSource {
    suspend fun insert(noteWidget: NoteWidgetEntry)
    suspend fun deleteById(noteWidgetId: Int)
    suspend fun getTitledNoteWidget(noteWidgetId: Int): TitledNoteWidgetEntry
    suspend fun update(noteWidget: NoteWidgetEntry)
    suspend fun updateWidgetNoteId(noteWidgetId: Int, noteId: Long)
    suspend fun getAllWidgetIds(): IntArray
    suspend fun getAllNoteWidgets(): LiveData<List<NoteWidgetEntry>>
}
