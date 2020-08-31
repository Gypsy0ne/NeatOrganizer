package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSchedulesDao
import org.junit.After
import org.junit.Before

class RoutineScheduleEntityTest {
    private lateinit var routineSchedulesDao: RoutineSchedulesDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        routineEntityDao = database.routinesDao()
    }

    @After
    fun finish() {
        database.close()
    }
}