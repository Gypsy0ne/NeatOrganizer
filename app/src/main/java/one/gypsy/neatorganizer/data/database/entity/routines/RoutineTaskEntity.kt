package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry

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
    val name: String,
    val done: Boolean,
    val routineId: Long,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)

fun RoutineTaskEntity.toRoutineTaskEntry() =
    RoutineTaskEntry(
        id = this.id,
        routineId = this.routineId,
        name = this.name,
        done = this.done
    )

