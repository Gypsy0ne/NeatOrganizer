package one.gypsy.neatorganizer.data.database.dao.notes

import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.notes.NoteWidgetEntity
import one.gypsy.neatorganizer.data.database.entity.notes.WidgetAndNote

@Dao
interface NoteWidgetsDao : BaseDao<NoteWidgetEntity> {

    @Query("SELECT * FROM note_widgets WHERE widgetId = :noteWidgetId")
    fun getWidgetWithNoteById(noteWidgetId: Int): WidgetAndNote

    @Query("DELETE FROM note_widgets WHERE widgetId = :noteWidgetId")
    fun deleteWidgetById(noteWidgetId: Int)
}
