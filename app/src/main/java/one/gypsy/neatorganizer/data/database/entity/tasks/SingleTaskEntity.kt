package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry

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
    override val createdAt: Long
) : Timestamped

fun SingleTaskEntity.toSingleTaskEntry() =
    SingleTaskEntry(
        id = this.id,
        name = this.name,
        done = this.done,
        groupId = this.groupId,
        createdAt = this.createdAt
    )
