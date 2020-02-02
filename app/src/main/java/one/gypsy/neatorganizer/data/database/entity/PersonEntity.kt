package one.gypsy.neatorganizer.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "people")
class PersonEntity(val name: String,
                   val sex: String,
                        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
                        val avatar: ByteArray? = null,
                        val lastInteraction: Int,
                        val dateOfBirth: Date,
                        @PrimaryKey(autoGenerate = true) var id: Long = 0)