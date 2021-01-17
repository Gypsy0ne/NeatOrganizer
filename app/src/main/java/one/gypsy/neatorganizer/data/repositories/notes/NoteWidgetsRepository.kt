package one.gypsy.neatorganizer.data.repositories.notes

import one.gypsy.neatorganizer.domain.datasource.notes.NoteWidgetsDataSource
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry

class NoteWidgetsRepository(private val dataSource: NoteWidgetsDataSource) {

    suspend fun insertNoteWidget(noteWidget: NoteWidgetEntry) = dataSource.insert(noteWidget)

    suspend fun deleteNoteWidgetById(noteWidgetId: Int) = dataSource.deleteById(noteWidgetId)

    suspend fun updateNoteWidget(noteWidget: NoteWidgetEntry) = dataSource.update(noteWidget)
}
