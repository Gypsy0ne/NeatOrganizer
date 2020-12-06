package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup

@Entity(tableName = "single_task_group")
data class SingleTaskGroupEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    override val createdAt: Long
) : Timestamped

fun SingleTaskGroupEntity.toSingleTaskGroup() =
    SingleTaskGroup(name, id = id, createdAt = this.createdAt)
