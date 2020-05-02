package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class RoutineEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)

