package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.room.Dao
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity

@Dao
interface SingleTasksDao:
    BaseDao<SingleTaskEntity>