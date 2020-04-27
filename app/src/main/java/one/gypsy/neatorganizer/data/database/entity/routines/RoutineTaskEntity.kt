package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Task

@Entity(
    tableName = "routine_tasks",
    foreignKeys = [ForeignKey(
        entity = RoutineEntity::class,
        parentColumns = ["id"],
        childColumns = ["routineId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class RoutineTaskEntity(
    override val name: String,
    override val done: Boolean,
    val routineId: Long,
    @PrimaryKey(autoGenerate = true) override var id: Long = 0
) : Task()