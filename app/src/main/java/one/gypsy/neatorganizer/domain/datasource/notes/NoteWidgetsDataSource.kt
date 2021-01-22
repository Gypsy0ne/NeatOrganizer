package one.gypsy.neatorganizer.domain.datasource.notes

import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.dto.notes.TitledNoteWidgetEntry

interface NoteWidgetsDataSource {
    suspend fun insert(noteWidget: NoteWidgetEntry)
    suspend fun deleteById(noteWidgetId: Int)
    suspend fun getTitledNoteWidget(noteWidgetId: Int): TitledNoteWidgetEntry
    suspend fun update(noteWidget: NoteWidgetEntry)
}
