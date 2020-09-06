package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ScheduledRoutineWithTasksTest {
    private lateinit var routineEntityDao: RoutinesDao
    private lateinit var routineScheduleEntityDao: RoutineSchedulesDao
    private lateinit var routineTaskEntityDao: RoutineTasksDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        routineEntityDao = database.routinesDao()
        routineScheduleEntityDao = database.routinesSchedulesDao()
        routineTaskEntityDao = database.routineTasksDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldGetScheduledRoutineWithTasks() {
        // given
        val routineId = 1L
        val scheduledRoutine = RoutineEntity("foobar", routineId)
        val routineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = true,
            wednesday = false,
            thursday = false,
            friday = true,
            saturday = true,
            sunday = true,
            routineId = routineId
        )
        val routineTasks = listOf(
            RoutineTaskEntity("taskOne", true, routineId = routineId, id = 1L),
            RoutineTaskEntity("taskTwo", true, routineId = routineId, id = 2L),
            RoutineTaskEntity("taskThree", false, routineId = routineId, id = 3L),
            RoutineTaskEntity("taskFour", true, routineId = routineId, id = 4L)
        )
        routineEntityDao.insert(scheduledRoutine)
        routineTaskEntityDao.insert(*routineTasks.toTypedArray())
        routineScheduleEntityDao.insert(routineSchedule)

        // when
        val allScheduledRoutinesWithTasks = routineEntityDao.getAllScheduledRoutinesWithTasks()

        // then
        assertThat(allScheduledRoutinesWithTasks).hasSize(1)
        allScheduledRoutinesWithTasks.first().apply {
            assertThat(routine).isEqualTo(scheduledRoutine)
            assertThat(tasks).isEqualTo(routineTasks)
            assertThat(schedule).isEqualTo(routineSchedule)
        }
    }

    @Test
    fun shouldMapToDomainModel() {
        // given
        val scheduledRoutineId = 1L
        val scheduledRoutine = RoutineEntity("foobar", scheduledRoutineId)
        val routineSchedule = RoutineScheduleEntity(
            monday = true,
            tuesday = true,
            wednesday = false,
            thursday = false,
            friday = true,
            saturday = true,
            sunday = true,
            routineId = scheduledRoutineId
        )
        val routineTasks = listOf(
            RoutineTaskEntity("taskOne", true, routineId = scheduledRoutineId, id = 1L),
            RoutineTaskEntity("taskTwo", true, routineId = scheduledRoutineId, id = 2L),
            RoutineTaskEntity("taskThree", false, routineId = scheduledRoutineId, id = 3L),
            RoutineTaskEntity("taskFour", true, routineId = scheduledRoutineId, id = 4L)
        )
        val scheduledRoutineWithTasks =
            ScheduledRoutineWithTasks(scheduledRoutine, routineTasks, routineSchedule)

        // when
        val domainScheduledRoutineWithTasks = scheduledRoutineWithTasks.toRoutine()

        //then
        assertThat(domainScheduledRoutineWithTasks.id).isEqualTo(scheduledRoutine.id)
        assertThat(domainScheduledRoutineWithTasks.name).isEqualTo(scheduledRoutine.name)
        domainScheduledRoutineWithTasks.schedule.apply {
            assertThat(routineId).isEqualTo(scheduledRoutine.id)
            assertThat(scheduledDays).isEqualTo(routineSchedule.let {
                listOf(
                    it.monday,
                    it.tuesday,
                    it.wednesday,
                    it.thursday,
                    it.friday,
                    it.saturday,
                    it.sunday
                )
            })
        }
        domainScheduledRoutineWithTasks.tasks.forEachIndexed { index, routineTaskEntry ->
            assertThat(routineTaskEntry.done).isEqualTo(routineTasks[index].done)
            assertThat(routineTaskEntry.id).isEqualTo(routineTasks[index].id)
            assertThat(routineTaskEntry.routineId).isEqualTo(routineTasks[index].routineId)
            assertThat(routineTaskEntry.name).isEqualTo(routineTasks[index].name)
        }
    }

    @Test
    fun shouldMapToEmptySchedule() {
        // given
        val scheduledRoutineId = 1L
        val scheduledRoutine = RoutineEntity("foobar", scheduledRoutineId)
        val routineSchedule = null
        val routineTasks = listOf(
            RoutineTaskEntity("taskOne", true, routineId = scheduledRoutineId, id = 1L),
            RoutineTaskEntity("taskTwo", true, routineId = scheduledRoutineId, id = 2L)
        )
        val scheduledRoutineWithTasks =
            ScheduledRoutineWithTasks(scheduledRoutine, routineTasks, routineSchedule)

        // when
        val domainScheduledRoutineWithTasks = scheduledRoutineWithTasks.toRoutine()

        // then
        assertThat(domainScheduledRoutineWithTasks.schedule.scheduledDays).isEqualTo(RoutineSchedule.EMPTY.scheduledDays)
        assertThat(domainScheduledRoutineWithTasks.schedule.routineId).isEqualTo(scheduledRoutineId)
    }
}