package one.gypsy.neatorganizer.database.entity.notes

import androidx.room.Embedded
import androidx.room.Relation

class WidgetAndNote(
    @Relation(
        parentColumn = "noteId",
        entityColumn = "id"
    )
    val note: NoteEntity,
    @Embedded
    val widget: NoteWidgetEntity
)
