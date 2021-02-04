package one.gypsy.neatorganizer.database.entity.tasks

import one.gypsy.neatorganizer.database.DatabaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class TaskWidgetEntityTest : DatabaseTest() {

    private lateinit var taskWidgetsDao: one.gypsy.neatorganizer.database.dao.tasks.TaskWidgetsDao
    private lateinit var taskGroupDao: one.gypsy.neatorganizer.database.dao.tasks.SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        taskWidgetsDao = database.taskWidgetDao()
        taskGroupDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldUpdateTaskWidget() {
        // given
        val updatedTaskGroupId = 423L
        val updatedWidgetId = 312
        val taskWidget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = updatedWidgetId,
            taskGroupId = 12,
            color = 312221
        )

        // when
        taskWidgetsDao.insert(taskWidget)
        taskWidgetsDao.update(taskWidget.copy(taskGroupId = updatedTaskGroupId))
        val updatedTaskWidget = taskWidgetsDao.getWidgetById(updatedWidgetId)

        // then
        assertThat(updatedTaskWidget.taskGroupId).isEqualTo(updatedTaskGroupId)
    }

    @Test
    fun shouldRemoveTaskWidget() {
        // given
        val taskWidget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = 312,
            taskGroupId = 12,
            color = 312221
        )

        // when
        taskWidgetsDao.insert(taskWidget)
        taskWidgetsDao.delete(taskWidget)
        val taskWidgetIds = taskWidgetsDao.getAllTaskWidgets()

        // then
        assertThat(taskWidgetIds).isEmpty()
    }

    @Test
    fun shouldInsertTaskWidget() {
        // given
        val taskWidget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = 312,
            taskGroupId = 12,
            color = 312221
        )

        // when
        taskWidgetsDao.insert(taskWidget)
        val fetchedTaskWidgets = taskWidgetsDao.getAllTaskWidgets()

        // then
        assertThat(fetchedTaskWidgets).hasSize(1)
        assertThat(fetchedTaskWidgets.first()).isEqualToComparingFieldByField(taskWidget)
    }

    @Test
    fun shouldGetWidgetById() {
        // given
        val fetchedWidgetId = 312
        val taskWidget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = fetchedWidgetId,
            taskGroupId = 12,
            color = 312221
        )

        // when
        taskWidgetsDao.insert(taskWidget)
        val fetchedTaskWidget = taskWidgetsDao.getWidgetById(fetchedWidgetId)

        // then
        assertThat(fetchedTaskWidget).isEqualToComparingFieldByField(taskWidget)
    }

    @Test
    fun shouldGetAllWidgetIds() {
        // given
        val widgets = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 12,
                color = 123123,
                taskGroupId = 66L
            ),
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 13,
                color = 123132,
                taskGroupId = 62L
            ),
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 14,
                color = 123634,
                taskGroupId = 64L
            ),
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 15,
                color = 123634,
                taskGroupId = 56L
            )
        )

        // when
        taskWidgetsDao.insert(*widgets)
        val taskWidgetIds = taskWidgetsDao.getAllWidgetIds()

        // then
        assertThat(taskWidgetIds).containsExactlyInAnyOrder(
            *widgets.map { it.widgetId }
                .toIntArray()
        )
    }

    @Test
    fun shouldDeleteWidgetById() {
        // given
        val deletedWidgetId = 1231
        val widget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = deletedWidgetId,
            taskGroupId = 99L,
            color = 768876
        )

        // when
        taskWidgetsDao.insert(widget)
        taskWidgetsDao.deleteWidgetById(deletedWidgetId)
        val widgets = taskWidgetsDao.getAllTaskWidgets()

        // then
        assertThat(widgets).isEmpty()
    }

    @Test
    fun shouldGetAllTaskWidgetsObservable() {
        // given
        val taskWidgets = arrayOf(
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 12,
                color = 3456786,
                taskGroupId = 35L
            ),
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 22,
                color = 3456786,
                taskGroupId = 38L
            ),
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 52,
                color = 3456786,
                taskGroupId = 32L
            ),
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = 2,
                color = 3456786,
                taskGroupId = 31L
            ),
        )

        // when
        taskWidgetsDao.insert(*taskWidgets)
        val taskWidgetsObservable = taskWidgetsDao.getAllTaskWidgetsObservable()

        // then
        taskWidgetsObservable.observeForever {
            assertThat(it).containsExactlyInAnyOrderElementsOf(taskWidgets.toList())
        }
    }

    @Test
    fun shouldGetTaskGroupIdByWidgetId() {
        // given
        val widgetTaskGroupId = 13L
        val taskWidgetId = 1

        // when
        taskWidgetsDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
                widgetId = taskWidgetId,
                taskGroupId = widgetTaskGroupId,
                color = 112233
            )
        )
        taskGroupDao.insert(
            one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity(
                name = "foobar",
                id = widgetTaskGroupId,
                createdAt = 12344122
            )
        )
        val taskGroupIdLinkedToWidget = taskWidgetsDao.getTaskGroupIdByWidgetId(taskWidgetId)

        // then
        assertThat(taskGroupIdLinkedToWidget).isEqualTo(widgetTaskGroupId)
    }

    @Test
    fun shouldMapToDomainModel() {
        // given
        val taskWidget = one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity(
            widgetId = 13,
            taskGroupId = 12L,
            color = 121233
        )

        // when
        val domainTaskWidget = taskWidget.toTaskWidgetEntry()

        // then
        with(domainTaskWidget) {
            assertThat(appWidgetId).isEqualTo(taskWidget.widgetId)
            assertThat(widgetColor).isEqualTo(taskWidget.color)
            assertThat(taskGroupId).isEqualTo(taskWidget.taskGroupId)
        }
    }
}
