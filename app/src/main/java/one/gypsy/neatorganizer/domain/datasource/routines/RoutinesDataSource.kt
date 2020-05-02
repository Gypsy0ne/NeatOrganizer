package one.gypsy.neatorganizer.domain.datasource.routines

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.routines.Routine

interface RoutinesDataSource {

    suspend fun add(routine: Routine): Long
    suspend fun remove(routine: Routine)
    suspend fun update(routine: Routine)
    suspend fun getAllRoutines(): LiveData<List<Routine>>
}
