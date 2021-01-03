package one.gypsy.neatorganizer.data.database.entity.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val title: String,
    val content: String = "",
    override val createdAt: Long
) : Timestamped

fun NoteEntity.toNoteEntry() = NoteEntry(id = id, title = title, createdAt = createdAt)

fun NoteEntity.toNote() = Note(id = id, content = content, title = title, createdAt = createdAt)
