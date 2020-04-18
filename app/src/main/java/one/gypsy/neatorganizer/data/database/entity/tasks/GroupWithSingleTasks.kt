package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity

data class GroupWithSingleTasks(@Embedded val group: SingleTaskGroupEntity, @Relation(parentColumn = "id", entityColumn = "groupId") val tasks: List<SingleTaskEntity>)