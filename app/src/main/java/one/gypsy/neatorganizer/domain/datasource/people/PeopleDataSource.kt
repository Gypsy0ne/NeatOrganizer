package one.gypsy.neatorganizer.domain.datasource.people

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.domain.dto.people.PersonProfile

interface PeopleDataSource {
    suspend fun add(personEntry: PersonEntry): Long

    suspend fun remove(personEntry: PersonEntry)

    suspend fun getAllPeopleEntries(): LiveData<List<PersonEntry>>

    suspend fun getPersonProfileById(personId: Long): LiveData<PersonProfile>

//    suspend fun getPersonHistory(personId: Long): LiveData<PersonHistory>
}