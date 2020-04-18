package one.gypsy.neatorganizer.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_tasks")
data class RoutineTaskEntity(
    override val description: String,
    override val done: Boolean,
    @PrimaryKey(autoGenerate = true) override var id: Long = 0
) : Task()