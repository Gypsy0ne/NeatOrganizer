package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Task

@Entity(
    tableName = "single_tasks",
    foreignKeys = [ForeignKey(
        entity = SingleTaskGroupEntity::class,
        parentColumns = ["id"],
        childColumns = ["groupId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class SingleTaskEntity(
    @PrimaryKey(autoGenerate = true) override var id: Long = 0,
    val groupId: Long,
    override val name: String,
    override val done: Boolean
) : Task()