package one.gypsy.neatorganizer.data.database.dao.people

import androidx.room.Dao
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity

@Dao
interface InteractionsDao:
    BaseDao<InteractionEntryEntity>