package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.data.database.entity.Task

@Entity(tableName = "single_tasks")
data class SingleTaskEntity(
    @PrimaryKey(autoGenerate = true) override var id: Long = 0,
    val groupId: Long,
    override val name: String,
    override val done: Boolean
    ) : Task()