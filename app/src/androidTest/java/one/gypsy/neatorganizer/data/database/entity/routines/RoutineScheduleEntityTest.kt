package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class RoutineScheduleEntityTest {
    private lateinit var routineSchedulesDao: RoutineSchedulesDao
    private lateinit var routinesDao: RoutinesDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        routinesDao = database.routinesDao()
        routineSchedulesDao = database.routinesSchedulesDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldInsertReplaceRoutineSchedule() {
        // given
        routinesDao.insert(RoutineEntity("foobar", 1))
        val routineScheduleId = 1L
        val routineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = false,
            wednesday = true,
            thursday = false,
            friday = true,
            saturday = false,
            sunday = true,
            routineId = routineScheduleId
        )

        // when
        routineSchedulesDao.insert(routineSchedule)
        val modifiedSchedule =
            routineSchedulesDao.getAllRoutineSchedules().first().copy(sunday = false)
        routineSchedulesDao.insert(modifiedSchedule)

        // then
        val selectedSchedules = routineSchedulesDao.getAllRoutineSchedules()
        assertThat(selectedSchedules).hasSize(1).containsExactly(modifiedSchedule)
        assertThat(selectedSchedules.first().routineId).isEqualTo(routineScheduleId)
    }

    @Test
    fun shouldInsertRoutineSchedule() {
        // given
        routinesDao.insert(RoutineEntity("foobar", 1))
        val routineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = false,
            wednesday = true,
            thursday = false,
            friday = true,
            saturday = false,
            sunday = true,
            routineId = 1
        )

        // when
        routineSchedulesDao.insert(routineSchedule)

        // then
        val selectedSchedules = routineSchedulesDao.getAllRoutineSchedules()
        assertThat(selectedSchedules).hasSize(1).containsExactly(routineSchedule)
    }

    @Test
    fun shouldDeleteRoutineSchedule() {
        // given
        routinesDao.insert(RoutineEntity("foobar", 1))
        val routineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = false,
            wednesday = true,
            thursday = false,
            friday = true,
            saturday = false,
            sunday = true,
            routineId = 1
        )
        val deletedRoutineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = false,
            wednesday = true,
            thursday = false,
            friday = false,
            saturday = false,
            sunday = false,
            routineId = 2
        )

        // when
        routineSchedulesDao.insert(routineSchedule)

        // then
        val selectedSchedules = routineSchedulesDao.getAllRoutineSchedules()
        assertThat(selectedSchedules).hasSize(1).containsExactly(routineSchedule)
    }

    @Test
    fun shouldUpdateRoutineSchedule() {
        // given
        routinesDao.insert(RoutineEntity("foobar", 1))
        val routineScheduleId = 1L
        val routineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = false,
            wednesday = true,
            thursday = false,
            friday = true,
            saturday = false,
            sunday = true,
            routineId = routineScheduleId
        )

        // when
        routineSchedulesDao.insert(routineSchedule)
        val updatedRoutineSchedule =
            routineSchedulesDao.getAllRoutineSchedules().first().copy(monday = false)
        routineSchedulesDao.update(updatedRoutineSchedule)

        // then
        val selectedSchedules = routineSchedulesDao.getAllRoutineSchedules()
        assertThat(selectedSchedules).hasSize(1).containsExactly(updatedRoutineSchedule)
        assertThat(selectedSchedules.first().routineId).isEqualTo(routineScheduleId)
    }

    @Test
    fun shouldGetAllRoutineSchedule() {
        // given
        routinesDao.insert(
            RoutineEntity("foobar", 1),
            RoutineEntity("foobar", 2),
            RoutineEntity("foobar", 3)
        )
        val routineSchedules = listOf(
            RoutineScheduleEntity(
                monday = true,
                tuesday = false,
                wednesday = true,
                thursday = false,
                friday = true,
                saturday = false,
                sunday = true,
                routineId = 1
            ),
            RoutineScheduleEntity(
                monday = true,
                tuesday = false,
                wednesday = false,
                thursday = false,
                friday = false,
                saturday = false,
                sunday = true,
                routineId = 2
            ),
            RoutineScheduleEntity(
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true,
                routineId = 3
            )
        )

        // when
        routineSchedulesDao.insert(*routineSchedules.toTypedArray())

        // then
        val selectedRoutineSchedules = routineSchedulesDao.getAllRoutineSchedules()
        assertThat(selectedRoutineSchedules).containsExactlyInAnyOrderElementsOf(
            selectedRoutineSchedules
        )
    }
}