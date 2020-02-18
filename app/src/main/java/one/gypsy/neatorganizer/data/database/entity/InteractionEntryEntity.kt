package one.gypsy.neatorganizer.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class InteractionEntryEntity(val personProfileId: Long,
                                  val creationDate: Date,
                                  @PrimaryKey(autoGenerate = true) val id: Long = 0)