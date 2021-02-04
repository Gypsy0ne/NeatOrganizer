package one.gypsy.neatorganizer.database.entity.tasks

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "single_tasks",
    foreignKeys = [
        ForeignKey(
            entity = SingleTaskGroupEntity::class,
            parentColumns = ["id"],
            childColumns = ["groupId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class SingleTaskEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val groupId: Long,
    val name: String,
    val done: Boolean,
    val createdAt: Long
)
