package one.gypsy.neatorganizer.data.database.entity.notes

import androidx.room.Embedded
import androidx.room.Relation

class WidgetAndNote(
    @Relation(
        parentColumn = "noteId",
        entityColumn = "id"
    )
    val note: NoteEntity,
    @Embedded
    val widget: NoteEntity
)

// fun WidgetAndTaskGroup.toTitledWidgetTaskEntry() = TitledTaskWidgetEntry(
//    appWidgetId = this.widget.widgetId,
//    taskGroupId = this.widget.taskGroupId,
//    widgetColor = this.widget.color,
//    taskGroupTitle = this.singleTaskGroup.name
// )
