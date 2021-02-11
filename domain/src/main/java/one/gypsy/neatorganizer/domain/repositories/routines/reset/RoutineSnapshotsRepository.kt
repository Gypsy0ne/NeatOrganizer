package one.gypsy.neatorganizer.domain.repositories.routines.reset

import one.gypsy.neatorganizer.domain.datasource.routines.reset.RoutineSnapshotsDataSource
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshot

class RoutineSnapshotsRepository(private val dataSource: RoutineSnapshotsDataSource) {
    suspend fun addRoutineSnapshot(snapshot: RoutineSnapshot) = dataSource.add(snapshot)
}
