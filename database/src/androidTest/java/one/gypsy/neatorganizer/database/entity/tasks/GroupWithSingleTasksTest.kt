package one.gypsy.neatorganizer.data.database.entity.tasks

import one.gypsy.neatorganizer.data.database.DatabaseTest
import one.gypsy.neatorganizer.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.database.entity.tasks.toSingleTaskEntry
import one.gypsy.neatorganizer.database.entity.tasks.toSingleTaskGroupEntry
import one.gypsy.neatorganizer.database.entity.tasks.toSingleTaskGroupWithTasks
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GroupWithSingleTasksTest : DatabaseTest() {

    private lateinit var tasksDao: SingleTasksDao
    private lateinit var taskGroupsDao: SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        tasksDao = database.singleTasksDao()
        taskGroupsDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldGetAllGroupsWithSingleTasks() {
        // given
        val taskGroups = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                name = "group1",
                id = 1L,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                name = "group2",
                id = 23L,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                name = "group3",
                id = 543L,
                createdAt = 123124
            ),
        )
        val tasks = arrayOf(
            listOf(
                one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                    groupId = 1L,
                    id = 11L,
                    name = "task1`",
                    done = true,
                    createdAt = 1234
                ),
                one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                    groupId = 1L,
                    id = 12L,
                    name = "task2",
                    done = false,
                    createdAt = 1224
                ),
                one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                    groupId = 1L,
                    id = 999L,
                    name = "task6",
                    done = true,
                    createdAt = 1224
                )
            ),
            listOf(
                one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                    groupId = 23L,
                    id = 31L,
                    name = "task3",
                    done = false,
                    createdAt = 1234
                ),
                one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                    groupId = 23L,
                    id = 51L,
                    name = "task4",
                    done = true,
                    createdAt = 1231
                )
            ),
            listOf(
                one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                    groupId = 543L,
                    id = 641L,
                    name = "task5",
                    done = false,
                    createdAt = 1231
                )
            )
        )

        // when
        taskGroupsDao.insert(*taskGroups)
        tasksDao.insert(*tasks.flatMap { it }.toTypedArray())
        val taskGroupsWithTasksObservable = taskGroupsDao.getAllGroupsWithSingleTasks()

        // then
        taskGroupsWithTasksObservable.observeForever { groupsWithTasks ->
            assertThat(groupsWithTasks.map { it.group }).containsExactlyInAnyOrderElementsOf(
                taskGroups.toList()
            )
            assertThat(groupsWithTasks.map { it.tasks }).containsExactlyInAnyOrderElementsOf(tasks.toList())
        }
    }

    @Test
    fun shouldGetGroupWithSingleTasksById() {
        // given
        val taskGroupId = 1L
        val tasks = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 123124
            )
        )
        val taskGroup = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
            name = "foobar",
            id = taskGroupId,
            createdAt = 123124
        )

        // when
        taskGroupsDao.insert(taskGroup)
        tasksDao.insert(*tasks)
        val taskGroupWithTasksObservable = taskGroupsDao.getGroupWithSingleTasksById(taskGroupId)

        // then
        taskGroupWithTasksObservable.observeForever {
            assertThat(it.group).isEqualToComparingFieldByField(taskGroup)
            assertThat(it.tasks).containsExactlyInAnyOrderElementsOf(tasks.toList())
        }
    }

    @Test
    fun shouldProperlyMapToGroupWithTasksDomainModel() {
        // given
        val taskGroupId = 1L
        val tasks = listOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 123124
            ),
        )
        val taskGroup = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
            name = "foobar",
            id = taskGroupId,
            createdAt = 123124
        )
        val groupWithSingleTasks =
            one.gypsy.neatorganizer.database.entity.tasks.GroupWithSingleTasks(
                tasks = tasks,
                group = taskGroup
            )

        // when
        val domainGroupWithSingleTasks = groupWithSingleTasks.toSingleTaskGroupWithTasks()

        // then
        with(domainGroupWithSingleTasks) {
            assertThat(groupWithSingleTasks.group.id).isEqualTo(id)
            assertThat(groupWithSingleTasks.group.createdAt).isEqualTo(createdAt)
            assertThat(groupWithSingleTasks.group.name).isEqualTo(name)
            assertThat(groupWithSingleTasks.tasks.map { it.toSingleTaskEntry() }).containsExactlyInAnyOrderElementsOf(
                this.tasks
            )
        }
    }

    @Test
    fun shouldProperlyMapToTaskGroupDomainModel() {
        // given
        val taskGroupId = 1L
        val tasks = listOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 123124
            )
        )
        val taskGroup = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
            name = "foobar",
            id = taskGroupId,
            createdAt = 123124
        )
        val groupWithSingleTasks =
            one.gypsy.neatorganizer.database.entity.tasks.GroupWithSingleTasks(
                tasks = tasks,
                group = taskGroup
            )

        // when
        val domainSingleTaskGroup = groupWithSingleTasks.toSingleTaskGroupEntry()

        // then
        with(domainSingleTaskGroup) {
            assertThat(id).isEqualTo(taskGroup.id)
            assertThat(name).isEqualTo(taskGroup.name)
            assertThat(tasksCount).isEqualTo(tasks.count())
            assertThat(tasksDone).isEqualTo(tasks.count { it.done })
        }
    }
}
