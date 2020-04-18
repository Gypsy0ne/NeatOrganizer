package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Task

@Entity(tableName = "single_tasks")
data class SingleTaskEntity(
    val groupId: Long,
    override val description: String,
    override val done: Boolean,
    @PrimaryKey(autoGenerate = true) override var id: Long = 0) : Task()