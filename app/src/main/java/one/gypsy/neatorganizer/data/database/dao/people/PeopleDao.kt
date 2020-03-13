package one.gypsy.neatorganizer.data.database.dao.people

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity
import one.gypsy.neatorganizer.data.database.entity.people.PersonWithInteractionHistory

@Dao
interface PeopleDao:
    BaseDao<PersonEntity> {

    @Query("SELECT * FROM people")
    fun getAllPeople(): LiveData<List<PersonEntity>>

    @Query("SELECT * FROM people WHERE id=:personId")
    fun getPersonById(personId: Long): LiveData<PersonEntity>

    @Query("SELECT * FROM people")
    fun getAllPeopleWithInteractionHsitory(): LiveData<List<PersonWithInteractionHistory>>
}