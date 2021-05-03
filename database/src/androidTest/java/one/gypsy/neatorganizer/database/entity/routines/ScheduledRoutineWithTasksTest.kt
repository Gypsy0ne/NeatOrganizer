package one.gypsy.neatorganizer.database.entity.routines

import one.gypsy.neatorganizer.database.DatabaseTest
import one.gypsy.neatorganizer.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.database.dao.routines.RoutinesDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

internal class ScheduledRoutineWithTasksTest : DatabaseTest() {
    private lateinit var routineEntityDao: RoutinesDao
    private lateinit var routineScheduleEntityDao: RoutineSchedulesDao
    private lateinit var routineTaskEntityDao: RoutineTasksDao

    @Before
    override fun setup() {
        super.setup()
        routineEntityDao = database.routinesDao()
        routineScheduleEntityDao = database.routinesSchedulesDao()
        routineTaskEntityDao = database.routineTasksDao()
    }

    @Test
    fun shouldGetScheduledRoutineWithTasks() {
        // given
        val routineId = 1L
        val scheduledRoutine = RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 123124
        )
        val routineSchedule =
            RoutineScheduleEntity(
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
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId = routineId,
                id = 1L,
                createdAt = 123124
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId = routineId,
                id = 2L,
                createdAt = 123124
            ),
            RoutineTaskEntity(
                "taskThree",
                false,
                routineId = routineId,
                id = 3L,
                createdAt = 1231
            ),
            RoutineTaskEntity(
                "taskFour",
                true,
                routineId = routineId,
                id = 4L,
                createdAt = 123124
            )
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
}
