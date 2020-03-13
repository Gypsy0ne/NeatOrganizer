package one.gypsy.neatorganizer.data.database.entity.people

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "interactions")
data class InteractionEntryEntity(val personProfileId: Long,
                                  val creationDate: Date,
                                  @PrimaryKey(autoGenerate = true) val id: Long = 0)