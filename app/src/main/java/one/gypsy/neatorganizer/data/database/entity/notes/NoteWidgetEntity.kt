package one.gypsy.neatorganizer.data.database.entity.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry

@Entity(tableName = "note_widgets")
data class NoteWidgetEntity(
    @PrimaryKey val widgetId: Int = 0,
    val noteId: Long,
    val color: Int
)

fun NoteWidgetEntity.toNoteWidgetEntry() =
    NoteWidgetEntry(widgetId = widgetId, noteId = noteId, color = color)
