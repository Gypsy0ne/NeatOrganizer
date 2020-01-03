package one.gypsy.neatorganizer.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import one.gypsy.neatorganizer.database.entity.PersonEntity

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: PersonEntity)

    @Query("SELECT * FROM people")
    fun getAllPeople(): LiveData<List<PersonEntity>>
}