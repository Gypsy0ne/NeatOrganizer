package one.gypsy.neatorganizer.domain.datasource.routines.reset

import one.gypsy.neatorganizer.domain.routines.reset.RoutineSnapshot

interface RoutineSnapshotsDataSource {
    suspend fun add(snapshot: RoutineSnapshot)
}
