package one.gypsy.neatorganizer.domain.datasource.routines.reset

import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotsDao
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshot
import one.gypsy.neatorganizer.domain.dto.routines.reset.toRoutineSnapshotEntity

class UserRoutineSnapshotsDataSource(private val routineSnapshotsDao: RoutineSnapshotsDao) :
    RoutineSnapshotsDataSource {
    override suspend fun add(snapshot: RoutineSnapshot): Long =
        routineSnapshotsDao.insert(snapshot.toRoutineSnapshotEntity())

}