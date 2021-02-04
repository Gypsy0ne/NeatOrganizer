package one.gypsy.neatorganizer.domain.datasource.routines.reset

import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshot
import one.gypsy.neatorganizer.domain.dto.routines.reset.toRoutineSnapshotEntity

class UserRoutineSnapshotsDataSource(private val routineSnapshotsDao: one.gypsy.neatorganizer.database.dao.routines.RoutineSnapshotsDao) :
    RoutineSnapshotsDataSource {
    override suspend fun add(snapshot: RoutineSnapshot) =
        routineSnapshotsDao.insert(snapshot.toRoutineSnapshotEntity())
}
