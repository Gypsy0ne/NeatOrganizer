package one.gypsy.neatorganizer.domain.datasource.routines.reset

import one.gypsy.neatorganizer.database.dao.routines.RoutineSnapshotsDao
import one.gypsy.neatorganizer.domain.routines.reset.RoutineSnapshot
import one.gypsy.neatorganizer.domain.routines.reset.toRoutineSnapshotEntity

class UserRoutineSnapshotsDataSource(private val routineSnapshotsDao: RoutineSnapshotsDao) :
    RoutineSnapshotsDataSource {
    override suspend fun add(snapshot: RoutineSnapshot) =
        routineSnapshotsDao.insert(snapshot.toRoutineSnapshotEntity())
}
