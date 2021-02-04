package one.gypsy.neatorganizer.database.dao.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.database.dao.BaseDao
import one.gypsy.neatorganizer.database.entity.notes.NoteWidgetEntity
import one.gypsy.neatorganizer.database.entity.notes.WidgetAndNote

@Dao
interface NoteWidgetsDao : BaseDao<NoteWidgetEntity> {

    // TODO unit test
    @Query("SELECT * FROM note_widgets WHERE widgetId = :noteWidgetId")
    fun getWidgetWithNoteById(noteWidgetId: Int): WidgetAndNote

    @Query("DELETE FROM note_widgets WHERE widgetId = :noteWidgetId")
    fun deleteWidgetById(noteWidgetId: Int)

    // TODO unit test
    @Query("UPDATE note_widgets SET noteId = :noteId WHERE widgetId = :noteWidgetId")
    fun updateLinkedTaskGroupById(noteWidgetId: Int, noteId: Long)

    // TODO unit test
    @Query("SELECT widgetId FROM note_widgets")
    fun getAllWidgetIds(): IntArray

    // TODO unit test
    @Query("SELECT * FROM note_widgets")
    fun getAllNoteWidgetsObservable(): LiveData<List<NoteWidgetEntity>>
}
