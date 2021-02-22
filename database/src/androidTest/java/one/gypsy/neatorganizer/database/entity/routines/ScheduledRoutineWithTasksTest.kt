package one.gypsy.neatorganizer.database.entity.routines

import one.gypsy.neatorganizer.domain.database.DatabaseTest
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ScheduledRoutineWithTasksTest : DatabaseTest() {
    private lateinit var routineEntityDao: one.gypsy.neatorganizer.database.dao.routines.RoutinesDao
    private lateinit var routineScheduleEntityDao: one.gypsy.neatorganizer.database.dao.routines.RoutineSchedulesDao
    private lateinit var routineTaskEntityDao: one.gypsy.neatorganizer.database.dao.routines.RoutineTasksDao

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

    @Test
    fun shouldMapToDomainModel() {
        // given
        val scheduledRoutineId = 1L
        val scheduledRoutine = RoutineEntity(
            "foobar",
            id = scheduledRoutineId,
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
                routineId = scheduledRoutineId
            )
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId = scheduledRoutineId,
                id = 1L,
                createdAt = 1231
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId = scheduledRoutineId,
                id = 2L,
                createdAt = 123
            ),
            RoutineTaskEntity(
                "taskThree",
                false,
                routineId = scheduledRoutineId,
                id = 3L,
                createdAt = 12
            ),
            RoutineTaskEntity(
                "taskFour",
                true,
                routineId = scheduledRoutineId,
                id = 4L,
                createdAt = 1231
            )
        )
        val scheduledRoutineWithTasks =
            ScheduledRoutineWithTasks(
                scheduledRoutine,
                routineTasks,
                routineSchedule
            )

        // when
        val domainScheduledRoutineWithTasks = scheduledRoutineWithTasks.toRoutineWithTasks()

        // then
        assertThat(domainScheduledRoutineWithTasks.id).isEqualTo(scheduledRoutine.id)
        assertThat(domainScheduledRoutineWithTasks.name).isEqualTo(scheduledRoutine.name)
        domainScheduledRoutineWithTasks.schedule.apply {
            assertThat(routineId).isEqualTo(scheduledRoutine.id)
            assertThat(scheduledDays).isEqualTo(
                routineSchedule.let {
                    listOf(
                        it.monday,
                        it.tuesday,
                        it.wednesday,
                        it.thursday,
                        it.friday,
                        it.saturday,
                        it.sunday
                    )
                }
            )
        }
        domainScheduledRoutineWithTasks.tasks.forEachIndexed { index, routineTaskEntry ->
            assertThat(routineTaskEntry.done).isEqualTo(routineTasks[index].done)
            assertThat(routineTaskEntry.id).isEqualTo(routineTasks[index].id)
            assertThat(routineTaskEntry.routineId).isEqualTo(routineTasks[index].routineId)
            assertThat(routineTaskEntry.name).isEqualTo(routineTasks[index].name)
            assertThat(routineTaskEntry.createdAt).isEqualTo(routineTasks[index].createdAt)
        }
    }

    @Test
    fun shouldMapToEmptySchedule() {
        // given
        val scheduledRoutineId = 1L
        val scheduledRoutine = RoutineEntity(
            "foobar",
            id = scheduledRoutineId,
            createdAt = 123124
        )
        val routineSchedule = null
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId = scheduledRoutineId,
                id = 1L,
                createdAt = 1124
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId = scheduledRoutineId,
                id = 2L,
                createdAt = 123
            )
        )
        val scheduledRoutineWithTasks =
            ScheduledRoutineWithTasks(
                scheduledRoutine,
                routineTasks,
                routineSchedule
            )

        // when
        val domainScheduledRoutineWithTasks = scheduledRoutineWithTasks.toRoutineWithTasks()

        // then
        assertThat(domainScheduledRoutineWithTasks.schedule.scheduledDays).isEqualTo(RoutineSchedule.EMPTY.scheduledDays)
        assertThat(domainScheduledRoutineWithTasks.schedule.routineId).isEqualTo(scheduledRoutineId)
    }
}
