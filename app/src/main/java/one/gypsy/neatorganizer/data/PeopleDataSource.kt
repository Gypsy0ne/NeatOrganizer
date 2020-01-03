package one.gypsy.neatorganizer.data

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.Person

interface PeopleDataSource {
    suspend fun add(person: Person)

    suspend fun remove(person: Person)

    suspend fun getAll(): LiveData<List<Person>>
}