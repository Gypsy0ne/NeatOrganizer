package one.gypsy.neatorganizer.data

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.domain.PersonHistory

interface PeopleDataSource {
    suspend fun add(person: Person)

    suspend fun remove(person: Person)

    suspend fun getAll(): LiveData<List<Person>>

//    suspend fun getPersonHistory(personId: Long): LiveData<PersonHistory>
}