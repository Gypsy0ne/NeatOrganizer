package one.gypsy.neatorganizer.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.entity.PersonEntity
import one.gypsy.neatorganizer.data.database.entity.PersonWithInteractionHistory

@Dao
interface PeopleDao: BaseDao<PersonEntity> {

    @Query("SELECT * FROM people")
    fun getAllPeople(): LiveData<List<PersonEntity>>

    @Query("SELECT * FROM people WHERE id=:personId")
    fun getPersonById(personId: Long): LiveData<PersonEntity>

    @Query("SELECT * FROM people")
    fun getAllPeopleWithInteractionHsitory(): LiveData<List<PersonWithInteractionHistory>>
}