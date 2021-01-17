package one.gypsy.neatorganizer.domain.datasource.notes

import one.gypsy.neatorganizer.data.database.dao.notes.NoteWidgetsDao
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.dto.notes.toNoteWidgetEntity

class UserNoteWidgetsDataSource(private val noteWidgetsDao: NoteWidgetsDao) :
    NoteWidgetsDataSource {

    override suspend fun insert(noteWidget: NoteWidgetEntry) =
        noteWidgetsDao.insert(noteWidget.toNoteWidgetEntity())

    override suspend fun deleteById(noteWidgetId: Int) =
        noteWidgetsDao.deleteWidgetById(noteWidgetId)

    override suspend fun update(noteWidget: NoteWidgetEntry) =
        noteWidgetsDao.update(noteWidget.toNoteWidgetEntity())
}
