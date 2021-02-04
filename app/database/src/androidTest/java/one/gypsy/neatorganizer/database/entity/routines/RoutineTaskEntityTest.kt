package one.gypsy.neatorganizer.database.entity.routines

import one.gypsy.neatorganizer.database.DatabaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RoutineTaskEntityTest : DatabaseTest() {
    private lateinit var routineTasksDao: one.gypsy.neatorganizer.database.dao.routines.RoutineTasksDao
    private lateinit var routinesDao: one.gypsy.neatorganizer.database.dao.routines.RoutinesDao

    @Before
    override fun setup() {
        super.setup()
        routinesDao = database.routinesDao()
        routineTasksDao = database.routineTasksDao()
    }

    @Test
    fun shouldInsertRoutineTask() {
        // given
        val routineTaskName = "foobar"
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask =
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                routineTaskName,
                taskDone,
                routineId = routineTaskId,
                id = taskId,
                createdAt = 12344122
            )
        routinesDao.insert(
            one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
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
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                routineTaskName,
                taskDone,
                routineId = routineTaskId,
                id = taskId,
                createdAt = 12344122
            )
        val replacedRoutineTask =
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "replaced",
                false,
                routineId = routineTaskId,
                id = taskId,
                createdAt = 12344122
            )
        routinesDao.insert(
            one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
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
        val routineTask = one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
            "foobar",
            true,
            routineId = routineTaskId,
            id = taskId,
            createdAt = 12344122
        )
        val deletedTask = one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
            "deleted",
            false,
            routineId = routineTaskId,
            id = deletedTaskId,
            createdAt = 12344122
        )
        routinesDao.insert(
            one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
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
        val routineTask = one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
            "foobar",
            taskDone,
            routineId = routineTaskId,
            id = taskId,
            createdAt = 12344122
        )
        val updatedRoutineTask = one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
            "replaced",
            false,
            routineId = routineTaskId,
            id = taskId,
            createdAt = 12344122
        )
        routinesDao.insert(
            one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
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
        val routine = one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 12344122
        )
        val routineTasks = listOf(
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskOne",
                true,
                routineId = routineId,
                id = 1,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskTwo",
                false,
                routineId,
                id = 2,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskThree",
                true,
                routineId,
                id = 3,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskFour",
                true,
                routineId,
                id = 4,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskFive",
                false,
                routineId,
                id = 5,
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
    fun shouldResetTasksStatus() {
        // given
        val routineId = 1L
        val routine = one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 12344122
        )
        val routineTasks = listOf(
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskOne",
                true,
                routineId,
                id = 1,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskTwo",
                true,
                routineId,
                id = 2,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskThree",
                true,
                routineId,
                id = 3,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskFour",
                true,
                routineId,
                id = 4,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskFive",
                true,
                routineId,
                id = 5,
                createdAt = 12344122
            )
        )
        routinesDao.insert(routine)
        routineTasksDao.insert(*routineTasks.toTypedArray())

        // when
        routineTasksDao.resetTasksStatus()

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks.map { it.done }).containsOnly(false)
    }

    @Test
    fun shouldDeleteRoutineWithTasks() {
        // given
        val routineId = 1L
        val routine = one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
            "foobar",
            id = routineId,
            createdAt = 12344122
        )
        val routineTasks = listOf(
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskOne",
                true,
                routineId,
                id = 1,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskTwo",
                true,
                routineId,
                id = 2,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskThree",
                true,
                routineId,
                id = 3,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskFour",
                true,
                routineId,
                id = 4,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
                "taskFive",
                true,
                routineId,
                id = 5,
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
    fun shouldMapEntityToDomainModel() {
        // given
        val routineTaskEntity = one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity(
            "foobar",
            true,
            routineId = 1,
            id = 1,
            createdAt = 12344122
        )

        // when
        val domainRoutineTask = routineTaskEntity.toRoutineTaskEntry()

        // then
        assertThat(routineTaskEntity.name).isEqualTo(domainRoutineTask.name)
        assertThat(routineTaskEntity.done).isEqualTo(domainRoutineTask.done)
        assertThat(routineTaskEntity.id).isEqualTo(domainRoutineTask.id)
        assertThat(routineTaskEntity.routineId).isEqualTo(domainRoutineTask.routineId)
        assertThat(routineTaskEntity.createdAt).isEqualTo(domainRoutineTask.createdAt)
    }
}
