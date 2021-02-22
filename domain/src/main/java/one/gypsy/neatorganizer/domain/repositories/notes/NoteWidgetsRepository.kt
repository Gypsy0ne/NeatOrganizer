package one.gypsy.neatorganizer.domain.repositories.notes

import one.gypsy.neatorganizer.domain.datasource.notes.NoteWidgetsDataSource
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntryDto
import one.gypsy.neatorganizer.domain.dto.notes.toNoteWidget

class NoteWidgetsRepository(private val dataSource: NoteWidgetsDataSource) {

    suspend fun insertNoteWidget(noteWidget: NoteWidgetEntryDto) =
        dataSource.insert(noteWidget.toNoteWidget())

    suspend fun deleteNoteWidgetById(noteWidgetId: Int) = dataSource.deleteById(noteWidgetId)

    suspend fun getTitledNoteWidget(noteWidgetId: Int) =
        dataSource.getTitledNoteWidget(noteWidgetId)

    suspend fun updateNoteWidget(noteWidget: NoteWidgetEntryDto) =
        dataSource.update(noteWidget.toNoteWidget())

    suspend fun updateWidgetNoteId(noteWidgetId: Int, noteId: Long) =
        dataSource.updateWidgetNoteId(noteWidgetId, noteId)

    suspend fun getAllWidgetIds() = dataSource.getAllWidgetIds()

    suspend fun getAllNoteWidgets() = dataSource.getAllNoteWidgets()
}
