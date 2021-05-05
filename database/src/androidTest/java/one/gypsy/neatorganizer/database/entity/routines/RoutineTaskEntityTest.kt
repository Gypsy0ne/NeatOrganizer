package one.gypsy.neatorganizer.database.entity.routines

import one.gypsy.neatorganizer.database.DatabaseTest
import one.gypsy.neatorganizer.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.database.dao.routines.RoutinesDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

internal class RoutineTaskEntityTest : DatabaseTest() {
    private lateinit var routineTasksDao: RoutineTasksDao
    private lateinit var routinesDao: RoutinesDao
    private lateinit var routineSchedulesDao: RoutineSchedulesDao

    @Before
    override fun setup() {
        super.setup()
        routinesDao = database.routinesDao()
        routineTasksDao = database.routineTasksDao()
        routineSchedulesDao = database.routinesSchedulesDao()
    }

    @Test
    fun shouldInsertRoutineTask() {
        // given
        val routineTaskName = "foobar"
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask =
            RoutineTaskEntity(
                routineTaskName,
                taskDone,
                routineId = routineTaskId,
                id = taskId,
                createdAt = 12344122
            )
        routinesDao.insert(
            RoutineEntity(
                "foobar",
                routineTaskId,
                createdAt = 12344122
            )
        )

        // when
        routineTasksDao.insert(routineTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(routineTaskName).isEqualTo(routineTaskName)
            assertThat(done).isEqualTo(taskDone)
            assertThat(routineId).isEqualTo(routineTaskId)
            assertThat(id).isEqualTo(taskId)
        }
    }

    @Test
    fun shouldInsertReplaceRoutineTask() {
        // given
        val routineTaskName = "foobar"
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask =
            RoutineTaskEntity(
                routineTaskName,
                taskDone,
                routineId = routineTaskId,
                id = taskId,
                createdAt = 12344122
            )
        val replacedRoutineTask =
            RoutineTaskEntity(
                "replaced",
                false,
                routineId = routineTaskId,
                id = taskId,
                createdAt = 12344122
            )
        routinesDao.insert(
            RoutineEntity(
                "foobar",
                id = routineTaskId,
                createdAt = 12344122
            )
        )
        routineTasksDao.insert(replacedRoutineTask)

        // when
        routineTasksDao.insert(routineTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(name).isEqualTo(routineTaskName)
            assertThat(done).isEqualTo(taskDone)
            assertThat(routineId).isEqualTo(routineTaskId)
            assertThat(id).isEqualTo(taskId)
        }
    }

    @Test
    fun shouldDeleteRoutineTask() {
        // given
        val routineTaskId = 1L
        val taskId = 1L
        val deletedTaskId = 2L
        val routineTask = RoutineTaskEntity(
            "foobar",
            true,
            routineId = routineTaskId,
            id = taskId,
            createdAt = 12344122
        )
        val deletedTask = RoutineTaskEntity(
            "deleted",
            false,
            routineId = routineTaskId,
            id = deletedTaskId,
            createdAt = 12344122
        )
        routinesDao.insert(
            RoutineEntity(
                "foobar",
                id = routineTaskId,
                createdAt = 12344122
            )
        )
        routineTasksDao.insert(deletedTask)
        routineTasksDao.insert(routineTask)

        // when
        routineTasksDao.delete(deletedTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(id).isNotEqualTo(deletedTaskId)
            assertThat(id).isEqualTo(taskId)
        }
    }

    @Test
    fun shouldUpdateRoutineTask() {
        // given
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask = RoutineTaskEntity(
            "foobar",
            taskDone,
            routineId = routineTaskId,
            id = taskId,
            createdAt = 12344122
        )
        val updatedRoutineTask = RoutineTaskEntity(
            "replaced",
            false,
            routineId = routineTaskId,
            id = taskId,
            createdAt = 12344122
        )
        routinesDao.insert(
            RoutineEntity(
                "foobar",
                id = routineTaskId,
                createdAt = 12344122
            )
        )
        routineTasksDao.insert(routineTask)

        // when
        routineTasksDao.update(updatedRoutineTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(name).isEqualTo(updatedRoutineTask.name)
            assertThat(done).isEqualTo(updatedRoutineTask.done)
            assertThat(routineId).isEqualTo(updatedRoutineTask.id)
            assertThat(id).isEqualTo(updatedRoutineTask.routineId)
        }
    }

    @Test
    fun shouldGetAllRoutineTasks() {
        // given
        val routineId = 1L
        val routine = RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 12344122
        )
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId = routineId,
                id = 1,
                createdAt = 12344122
            ),
            RoutineTaskEntity(
                "taskTwo",
                false,
                routineId,
                id = 2,
                createdAt = 12344122
            )
        )
        routinesDao.insert(routine)

        // when
        routineTasksDao.insert(*routineTasks.toTypedArray())

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).containsExactlyInAnyOrderElementsOf(routineTasks)
    }

    @Test
    fun shouldDeleteRoutineWithTasks() {
        // given
        val routineId = 1L
        val routine = RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 12344122
        )
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId,
                id = 1,
                createdAt = 12344122
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId,
                id = 2,
                createdAt = 12344122
            )
        )
        routinesDao.insert(routine)
        routineTasksDao.insert(*routineTasks.toTypedArray())

        // when
        routinesDao.delete(routine)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).isEmpty()
    }

    @Test
    fun shouldResetMondayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, monday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, tuesday = true)).insertToDatabase()

        // when
        routineTasksDao.resetMondayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    @Test
    fun shouldResetTuesdayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, tuesday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, monday = true)).insertToDatabase()

        // when
        routineTasksDao.resetTuesdayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    @Test
    fun shouldResetWednesdayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, wednesday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, monday = true)).insertToDatabase()

        // when
        routineTasksDao.resetWednesdayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    @Test
    fun shouldResetThursdayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, thursday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, monday = true)).insertToDatabase()

        // when
        routineTasksDao.resetThursdayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    @Test
    fun shouldResetFridayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, friday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, monday = true)).insertToDatabase()

        // when
        routineTasksDao.resetFridayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    @Test
    fun shouldResetSaturdayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, saturday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, monday = true)).insertToDatabase()

        // when
        routineTasksDao.resetSaturdayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    @Test
    fun shouldResetSundayTasksStatus() {
        // given
        createCompleteRoutine(1L, createScheduleEntity(1L, sunday = true)).insertToDatabase()
        createCompleteRoutine(2L, createScheduleEntity(2L, monday = true)).insertToDatabase()

        // when
        routineTasksDao.resetSundayTasksProgress()
        val selectedTasks = routineTasksDao.getAllRoutineTasks().groupBy { it.routineId }

        // then
        assertThat(selectedTasks[1]?.map { it.done }).containsOnly(false)
        assertThat(selectedTasks[2]?.map { it.done }).containsOnly(true)
    }

    private fun ScheduledRoutineWithTasks.insertToDatabase() {
        routinesDao.insert(routine)
        routineTasksDao.insert(*tasks.toTypedArray())
        schedule?.let { routineSchedulesDao.insert(it) }
    }

    private fun createCompleteRoutine(
        routineId: Long,
        schedule: RoutineScheduleEntity
    ): ScheduledRoutineWithTasks {
        val routine = RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 12344122
        )
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId,
                id = 0,
                createdAt = 12344122
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId,
                id = 0,
                createdAt = 12344122
            )
        )
        return ScheduledRoutineWithTasks(routine, routineTasks, schedule)
    }

    private fun createScheduleEntity(
        routineId: Long,
        monday: Boolean = false,
        tuesday: Boolean = false,
        wednesday: Boolean = false,
        thursday: Boolean = false,
        friday: Boolean = false,
        saturday: Boolean = false,
        sunday: Boolean = false
    ) = RoutineScheduleEntity(
        routineId = routineId,
        monday = monday,
        tuesday = tuesday,
        wednesday = wednesday,
        thursday = thursday,
        friday = friday,
        saturday = saturday,
        sunday = sunday
    )
}
