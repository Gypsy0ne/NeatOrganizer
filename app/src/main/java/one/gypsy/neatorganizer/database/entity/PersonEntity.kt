package one.gypsy.neatorganizer.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "people")
data class PersonEntity(val name: String,
                        val lastInteraction: Int,
                        val dateOfBirth: Date,
                        @PrimaryKey(autoGenerate = true) var id: Int = 0) {

}