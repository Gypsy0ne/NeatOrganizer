package one.gypsy.neatorganizer.data.datasource.routines.reset

import one.gypsy.neatorganizer.data.model.routines.reset.RoutineSnapshot

interface RoutineSnapshotsDataSource {
    suspend fun add(snapshot: RoutineSnapshot)
    suspend fun getLastRoutineSnapshot(): RoutineSnapshot?
}
