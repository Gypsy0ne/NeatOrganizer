package one.gypsy.neatorganizer.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "single_tasks")
data class SingleTaskEntity(
    override val description: String,
    override val done: Boolean,
    @PrimaryKey(autoGenerate = true) override var id: Long = 0
) : Task()