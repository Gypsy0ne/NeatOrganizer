package one.gypsy.neatorganizer.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.database.entity.Timestamped

@Entity(tableName = "single_task_group")
data class SingleTaskGroupEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    override val createdAt: Long
) : Timestamped
