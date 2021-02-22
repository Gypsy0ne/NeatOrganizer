package one.gypsy.neatorganizer.domain.database.entity.tasks

import one.gypsy.neatorganizer.database.entity.tasks.toTitledTaskWidgetEntry
import one.gypsy.neatorganizer.domain.database.DatabaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class WidgetAndTaskGroupTest : DatabaseTest() {

    private lateinit var taskWidgetsDao: one.gypsy.neatorganizer.database.dao.tasks.TaskWidgetsDao
    private lateinit var taskGroupDao: one.gypsy.neatorganizer.database.dao.tasks.SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        taskWidgetsDao = database.taskWidgetDao()
        taskGroupDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByIdObservable() {
        // given
        val widgetId = 22
        val taskGroupId = 12L
        val widget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = widgetId,
            color = 11221122,
            taskGroupId = taskGroupId
        )
        val taskGroup = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
            "foobar",
            id = taskGroupId,
            createdAt = 123124
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(taskGroup)
        val selectedWidgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupByIdObservable(
            widgetId
        )

        // then
        selectedWidgetWithTaskGroup.observeForever {
            assertThat(it.widget).isEqualToComparingFieldByField(widget)
            assertThat(it.singleTaskGroup).isEqualToComparingFieldByField(taskGroup)
        }
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByWidgetId() {
        // given
        val widgetId = 22
        val taskGroupId = 12L
        val widget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = widgetId,
            color = 11221122,
            taskGroupId = taskGroupId
        )
        val taskGroup = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
            "foobar",
            id = taskGroupId,
            createdAt = 123124
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(taskGroup)
        val selectedWidgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(selectedWidgetWithTaskGroup.widget)
            .isEqualToComparingFieldByField(widget)
        assertThat(selectedWidgetWithTaskGroup.singleTaskGroup)
            .isEqualToComparingFieldByField(
                taskGroup
            )
    }

    @Test
    fun shouldUpdateLinkedTaskGroupById() {
        // given
        val widgetId = 22
        val taskGroupId = 12L
        val swappedTaskGroupId = 22L
        val widget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = widgetId,
            color = 11221122,
            taskGroupId = taskGroupId
        )
        val taskGroups = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "foobar",
                id = taskGroupId,
                createdAt = 123124
            ),
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                "foobar",
                id = swappedTaskGroupId,
                createdAt = 123124
            )
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(*taskGroups)
        taskWidgetsDao.updateLinkedTaskGroupById(widgetId, swappedTaskGroupId)
        val widgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(widgetWithTaskGroup.singleTaskGroup.id).isEqualTo(swappedTaskGroupId)
    }

    @Test
    fun shouldMapToDomainModel() {
        // given
        val taskWidget = one.gypsy.neatorganizer.database.entity.tasks.WidgetAndTaskGroup(
            widget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 13,
                taskGroupId = 12L,
                color = 121233
            ),
            singleTaskGroup = one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                name = "foobar",
                id = 12L,
                createdAt = 123124
            )
        )

        // when
        val domainWidgetAndGroup = taskWidget.toTitledTaskWidgetEntry()

        // then
        with(domainWidgetAndGroup) {
            assertThat(taskGroupTitle).isEqualTo(taskWidget.singleTaskGroup.name)
            assertThat(appWidgetId).isEqualTo(taskWidget.widget.widgetId)
            assertThat(widgetColor).isEqualTo(taskWidget.widget.color)
            assertThat(taskGroupId).isEqualTo(taskWidget.widget.taskGroupId)
        }
    }
}
