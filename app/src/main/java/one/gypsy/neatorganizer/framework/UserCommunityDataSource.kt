package one.gypsy.neatorganizer.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import one.gypsy.neatorganizer.data.PeopleDataSource
import one.gypsy.neatorganizer.database.dao.PersonDao
import one.gypsy.neatorganizer.database.entity.PersonEntity
import one.gypsy.neatorganizer.domain.Person
import javax.inject.Inject

class UserCommunityDataSource(@JvmField @Inject var personDao: PersonDao) : PeopleDataSource {


    override suspend fun add(person: Person) {
        personDao.insert(PersonEntity(person.name, person.lastInteraction, person.dateOfBirth))
    }

    override suspend fun remove(person: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(): LiveData<List<Person>> = MutableLiveData<List<Person>>().apply {
        value = personDao.getAllPeople().value?.map {
            Person(
                it.name,
                it.lastInteraction,
                it.dateOfBirth
            )
        }
    }

}