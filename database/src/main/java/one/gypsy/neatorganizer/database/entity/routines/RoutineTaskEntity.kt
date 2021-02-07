package one.gypsy.neatorganizer.database.entity.routines

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.database.entity.Timestamped

@Entity(
    tableName = "routine_tasks",
    foreignKeys = [
        ForeignKey(
            entity = RoutineEntity::class,
            parentColumns = ["id"],
            childColumns = ["routineId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class RoutineTaskEntity(
    val name: String,
    val done: Boolean,
    val routineId: Long,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    override val createdAt: Long
) : Timestamped
