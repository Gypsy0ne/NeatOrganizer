package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.database.entity.notes.NoteEntity
import one.gypsy.neatorganizer.database.entity.notes.NoteWidgetEntity
import one.gypsy.neatorganizer.database.entity.notes.WidgetAndNote

fun NoteEntity.toNote() = Note(
    id = id,
    content = content,
    title = title,
    createdAt = createdAt,
    color = color
)

fun NoteEntity.toNoteEntry() = NoteEntry(
    id = id,
    title = title,
    createdAt = createdAt,
    color = color
)

fun NoteWidgetEntity.toNoteWidgetEntry() =
    NoteWidgetEntry(widgetId = widgetId, noteId = noteId, color = color)

fun WidgetAndNote.toTitledNoteWidgetEntry() = TitledNoteWidgetEntry(
    appWidgetId = this.widget.widgetId,
    noteId = this.widget.noteId,
    widgetColor = this.widget.color,
    noteTitle = this.note.title,
    noteContent = this.note.content
)
