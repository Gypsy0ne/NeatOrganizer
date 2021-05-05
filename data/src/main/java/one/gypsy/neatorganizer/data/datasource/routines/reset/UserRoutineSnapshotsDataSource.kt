package one.gypsy.neatorganizer.data.datasource.routines.reset

import one.gypsy.neatorganizer.data.model.routines.reset.RoutineSnapshot
import one.gypsy.neatorganizer.data.model.routines.reset.toRoutineSnapshotEntity
import one.gypsy.neatorganizer.data.model.routines.toRoutineSnapshot
import one.gypsy.neatorganizer.database.dao.routines.RoutineSnapshotsDao

internal class UserRoutineSnapshotsDataSource(private val routineSnapshotsDao: RoutineSnapshotsDao) :
    RoutineSnapshotsDataSource {
    override suspend fun add(snapshot: RoutineSnapshot) =
        routineSnapshotsDao.insert(snapshot.toRoutineSnapshotEntity())

    override suspend fun getLastRoutineSnapshot() =
        routineSnapshotsDao.getLastResetEntry()?.toRoutineSnapshot()
}
