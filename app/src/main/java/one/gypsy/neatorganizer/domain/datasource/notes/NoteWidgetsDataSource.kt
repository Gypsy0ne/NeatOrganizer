package one.gypsy.neatorganizer.domain.datasource.notes

import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry

interface NoteWidgetsDataSource {
    suspend fun insert(noteWidget: NoteWidgetEntry)
    suspend fun deleteById(noteWidgetId: Int)
    suspend fun update(noteWidget: NoteWidgetEntry)
}
