package one.gypsy.neatorganizer.data.database.dao

import androidx.room.Dao
import one.gypsy.neatorganizer.data.database.entity.InteractionEntryEntity
import one.gypsy.neatorganizer.domain.dto.InteractionEntry

@Dao
interface InteractionDao: BaseDao<InteractionEntryEntity> {
}