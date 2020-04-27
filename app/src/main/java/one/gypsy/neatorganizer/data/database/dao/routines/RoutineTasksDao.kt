package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Dao
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineTaskEntity

@Dao
interface RoutineTasksDao :
    BaseDao<RoutineTaskEntity>