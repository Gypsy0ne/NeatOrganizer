package one.gypsy.neatorganizer.domain.datasource

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.data.database.entity.PersonEntity
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.dto.PersonProfile

interface PeopleDataSource {
    suspend fun add(personEntry: PersonEntry)

    suspend fun remove(personEntry: PersonEntry)

    suspend fun getAllPeopleEntries(): LiveData<List<PersonEntry>>

    suspend fun getPersonProfileById(personId: Long): LiveData<PersonProfile>

//    suspend fun getPersonHistory(personId: Long): LiveData<PersonHistory>
}