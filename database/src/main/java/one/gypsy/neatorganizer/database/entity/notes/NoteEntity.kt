package one.gypsy.neatorganizer.database.entity.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.database.entity.Timestamped

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val title: String,
    val content: String = "",
    override val createdAt: Long,
    val color: Int
) : Timestamped
