package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "single_task_group")
data class SingleTaskGroupEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0)