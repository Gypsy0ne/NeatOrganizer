package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule

@Entity(
    tableName = "routine_schedules",
    foreignKeys = [ForeignKey(
        entity = RoutineEntity::class,
        parentColumns = ["id"],
        childColumns = ["routineId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class RoutineScheduleEntity(
    val monday: Boolean,
    val tuesday: Boolean,
    val wednesday: Boolean,
    val thursday: Boolean,
    val friday: Boolean,
    val saturday: Boolean,
    val sunday: Boolean,
    val routineId: Long,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)

fun RoutineScheduleEntity.toRoutineSchedule() = RoutineSchedule(
    id = this.id,
    routineId = this.routineId,
    scheduledDays = listOf(
        monday,
        thursday,
        wednesday,
        thursday,
        friday,
        saturday,
        sunday
    )
)