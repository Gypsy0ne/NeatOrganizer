package one.gypsy.neatorganizer.domain.datasource

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.Person

interface PeopleDataSource {
    suspend fun add(person: Person)

    suspend fun remove(person: Person)

    suspend fun getAll(): LiveData<List<Person>>

//    suspend fun getPersonHistory(personId: Long): LiveData<PersonHistory>
}