package one.gypsy.neatorganizer.database.entity.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_widgets")
data class NoteWidgetEntity(
    @PrimaryKey val widgetId: Int = 0,
    val noteId: Long,
    val color: Int
)
