package one.gypsy.neatorganizer.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.database.entity.Timestamped
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class SingleTaskGroupEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    override val createdAt: Long
) : Timestamped {
    companion object {
        const val TABLE_NAME = "single_task_group"
        const val NAME_FIELD = "name"
        const val CREATED_AT_FIELD = "createdAt"
    }
}
