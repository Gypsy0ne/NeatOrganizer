package one.gypsy.neatorganizer.database.entity.tasks

import one.gypsy.neatorganizer.database.DatabaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class SingleTaskEntityTest : DatabaseTest() {

    private lateinit var tasksDao: one.gypsy.neatorganizer.database.dao.tasks.SingleTasksDao
    private lateinit var taskGroupsDao: one.gypsy.neatorganizer.database.dao.tasks.SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        tasksDao = database.singleTasksDao()
        taskGroupsDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldInsertSingleTask() {
        // given
        val taskGroupId = 12L
        val singleTask = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
            id = 10L,
            groupId = taskGroupId,
            name = "foobar",
            done = true,
            createdAt = 12344122
        )

        // when
        taskGroupsDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "bar",
                id = taskGroupId,
                createdAt = 12344122
            )
        )
        tasksDao.insert(singleTask)
        val tasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(tasks).hasSize(1)
        assertThat(tasks.first()).isEqualToComparingFieldByField(singleTask)
    }

    @Test
    fun shouldDeleteSingleTask() {
        // given
        val taskGroupId = 12L
        val singleTask = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
            id = 10L,
            groupId = taskGroupId,
            name = "foobar",
            done = true,
            createdAt = 12344122
        )

        // when
        taskGroupsDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "bar",
                id = taskGroupId,
                createdAt = 12344122
            )
        )
        tasksDao.insert(singleTask)
        tasksDao.delete(singleTask)
        val tasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(tasks).hasSize(0)
    }

    @Test
    fun shouldUpdateSingleTask() {
        // given
        val taskGroupId = 12L
        val updatedTaskName = "updated"
        val singleTask = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
            id = 10L,
            groupId = taskGroupId,
            name = "foobar",
            done = true,
            createdAt = 12344122
        )

        // when
        taskGroupsDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "bar",
                id = taskGroupId,
                createdAt = 12344122
            )
        )
        tasksDao.insert(singleTask)
        val updatedTask = singleTask.copy(name = updatedTaskName)
        tasksDao.update(updatedTask)
        val tasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(tasks).hasSize(1)
        assertThat(tasks.first()).isEqualToComparingFieldByField(updatedTask)
    }

    @Test
    fun shouldGetAllSingleTasksByGroupId() {
        // given
        val taskGroupId = 12L
        val tasks = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 24L,
                name = "foobar4",
                done = true,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 55L,
                name = "foobar5",
                done = true,
                createdAt = 12344122
            )
        )

        // when
        taskGroupsDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "bar",
                id = taskGroupId,
                createdAt = 12344122
            )
        )
        tasksDao.insert(*tasks)
        val fetchedTasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(fetchedTasks).containsExactlyInAnyOrderElementsOf(tasks.toList())
    }

    @Test
    fun shouldGetAllSingleTasksByGroupIdObservable() {
        // given
        val taskGroupId = 12L
        val tasks = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 24L,
                name = "foobar4",
                done = true,
                createdAt = 12344122
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 55L,
                name = "foobar5",
                done = true,
                createdAt = 12344122
            )
        )

        // when
        taskGroupsDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "bar",
                id = taskGroupId,
                createdAt = 12344122
            )
        )
        tasksDao.insert(*tasks)
        val fetchedTasksObservable = tasksDao.getAllSingleTasksByGroupIdObservable(taskGroupId)

        // then
        fetchedTasksObservable.observeForever {
            assertThat(it).containsExactlyInAnyOrderElementsOf(tasks.toList())
        }
    }

    @Test
    fun shouldProperlyMapToDomainModel() {
        // given
        val singleTaskEntity = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
            groupId = 1L,
            id = 11L,
            name = "foobar1",
            done = true,
            createdAt = 12344122
        )

        // when
        val domainSingleTask = singleTaskEntity.toSingleTaskEntry()

        // then
        with(domainSingleTask) {
            assertThat(singleTaskEntity.done).isEqualTo(done)
            assertThat(singleTaskEntity.groupId).isEqualTo(groupId)
            assertThat(singleTaskEntity.id).isEqualTo(id)
            assertThat(singleTaskEntity.name).isEqualTo(name)
            assertThat(singleTaskEntity.createdAt).isEqualTo(createdAt)
        }
    }
}
