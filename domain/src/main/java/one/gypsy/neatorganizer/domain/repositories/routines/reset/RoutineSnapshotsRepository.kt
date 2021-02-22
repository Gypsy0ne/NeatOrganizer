package one.gypsy.neatorganizer.domain.repositories.routines.reset

import one.gypsy.neatorganizer.domain.datasource.routines.reset.RoutineSnapshotsDataSource
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotDto
import one.gypsy.neatorganizer.domain.dto.routines.reset.toRoutineSnapshot

class RoutineSnapshotsRepository(private val dataSource: RoutineSnapshotsDataSource) {
    suspend fun addRoutineSnapshot(snapshot: RoutineSnapshotDto) =
        dataSource.add(snapshot.toRoutineSnapshot())
}
