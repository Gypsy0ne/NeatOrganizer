package one.gypsy.neatorganizer.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//TODO make data class out of it to work with diffutil
@Entity(tableName = "people")
class PersonEntity(val name: String,
                        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
                        val avatar: ByteArray? = null,
                        val lastInteraction: Int,
                        val dateOfBirth: Date,
                        @PrimaryKey(autoGenerate = true) var id: Long = 0)