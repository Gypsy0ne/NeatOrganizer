package one.gypsy.neatorganizer.data.model.tasks

import one.gypsy.neatorganizer.database.entity.tasks.GroupWithSingleTasks
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EntityExtensionsKtTest {

    @Test
    fun shouldProperlyMapToGroupWithTasksDataModel() {
        // given
        val taskGroupId = 1L
        val tasks = listOf(
            SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 123124
            ),
            SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 123124
            ),
            SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 123124
            ),
        )
        val taskGroup = SingleTaskGroupEntity(
            name = "foobar",
            id = taskGroupId,
            createdAt = 123124
        )
        val groupWithSingleTasks = GroupWithSingleTasks(
            tasks = tasks,
            group = taskGroup
        )

        // when
        val dataGroupWithSingleTasks = groupWithSingleTasks.toSingleTaskGroupWithTasks()

        // then
        with(dataGroupWithSingleTasks) {
            assertThat(groupWithSingleTasks.group.id).isEqualTo(taskGroup.id)
            assertThat(groupWithSingleTasks.group.createdAt).isEqualTo(taskGroup.createdAt)
            assertThat(groupWithSingleTasks.group.name).isEqualTo(taskGroup.name)
            assertThat(groupWithSingleTasks.tasks.map { it.toSingleTaskEntry() }).containsExactlyInAnyOrderElementsOf(
                this.tasks
            )
        }
    }

    @Test
    fun shouldProperlyMapToTaskGroupDataModel() {
        // given
        val taskGroupId = 1L
        val tasks = listOf(
            SingleTaskEntity(
                groupId = taskGroupId,
                id = 11L,
                name = "foobar1",
                done = true,
                createdAt = 123124
            ),
            SingleTaskEntity(
                groupId = taskGroupId,
                id = 12L,
                name = "foobar2",
                done = false,
                createdAt = 123124
            ),
            SingleTaskEntity(
                groupId = taskGroupId,
                id = 33L,
                name = "foobar3",
                done = false,
                createdAt = 123124
            )
        )
        val taskGroup = SingleTaskGroupEntity(
            name = "foobar",
            id = taskGroupId,
            createdAt = 123124
        )
        val groupWithSingleTasks = GroupWithSingleTasks(
            tasks = tasks,
            group = taskGroup
        )

        // when
        val dataSingleTaskGroup = groupWithSingleTasks.toSingleTaskGroupEntry()

        // then
        with(dataSingleTaskGroup) {
            assertThat(id).isEqualTo(taskGroup.id)
            assertThat(name).isEqualTo(taskGroup.name)
            assertThat(tasksCount).isEqualTo(tasks.count())
            assertThat(tasksDone).isEqualTo(tasks.count { it.done })
        }
    }

    @Test
    fun shouldProperlyMapToDataModel() {
        // given
        val singleTaskEntity = SingleTaskEntity(
            groupId = 1L,
            id = 11L,
            name = "foobar1",
            done = true,
            createdAt = 12344122
        )

        // when
        val dataSingleTask = singleTaskEntity.toSingleTaskEntry()

        // then
        with(dataSingleTask) {
            assertThat(singleTaskEntity.done).isEqualTo(done)
            assertThat(singleTaskEntity.groupId).isEqualTo(groupId)
            assertThat(singleTaskEntity.id).isEqualTo(id)
            assertThat(singleTaskEntity.name).isEqualTo(name)
            assertThat(singleTaskEntity.createdAt).isEqualTo(createdAt)
        }
    }

    @Test
    fun shouldMapTaskGroupToDataModel() {
        // given
        val taskGroupEntity = SingleTaskGroupEntity(
            id = 1L,
            name = "foobar",
            createdAt = 123124
        )

        // when
        val dataTaskGroup = taskGroupEntity.toSingleTaskGroup()

        // then
        with(dataTaskGroup) {
            assertThat(taskGroupEntity.id).isEqualTo(id)
            assertThat(taskGroupEntity.name).isEqualTo(name)
            assertThat(taskGroupEntity.createdAt).isEqualTo(createdAt)
        }
    }

    @Test
    fun shouldMapTaskWidgetToDataModel() {
        // given
        val taskWidget = TaskWidgetEntity(
            widgetId = 13,
            taskGroupId = 12L,
            color = 121233
        )

        // when
        val dataTaskWidget = taskWidget.toTaskWidgetEntry()

        // then
        with(dataTaskWidget) {
            assertThat(appWidgetId).isEqualTo(taskWidget.widgetId)
            assertThat(widgetColor).isEqualTo(taskWidget.color)
            assertThat(taskGroupId).isEqualTo(taskWidget.taskGroupId)
        }
    }
}
