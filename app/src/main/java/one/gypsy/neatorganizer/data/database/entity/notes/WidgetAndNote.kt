package one.gypsy.neatorganizer.data.database.entity.notes

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.domain.dto.notes.TitledNoteWidgetEntry

class WidgetAndNote(
    @Relation(
        parentColumn = "noteId",
        entityColumn = "id"
    )
    val note: NoteEntity,
    @Embedded
    val widget: NoteWidgetEntity
)

fun WidgetAndNote.toTitledNoteWidgetEntry() = TitledNoteWidgetEntry(
    appWidgetId = this.widget.widgetId,
    noteId = this.widget.noteId,
    widgetColor = this.widget.color,
    noteTitle = this.note.title,
    noteContent = this.note.content
)
