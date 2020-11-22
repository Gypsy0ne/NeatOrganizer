package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class TaskWidgetEntityTest {

    private lateinit var taskWidgetsDao: TaskWidgetsDao
    private lateinit var taskGroupDao: SingleTaskGroupsDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        taskWidgetsDao = database.taskWidgetDao()
        taskGroupDao = database.singleTaskGroupsDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByWidgetId() {
        // given
        val widgetId = 63
        val widgetColor = 11221122
        val taskGroupId = 12L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = widgetColor,
            taskGroupId = taskGroupId
        )
        val taskGroup = SingleTaskGroupEntity("foobar", taskGroupId)

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(taskGroup)
        val selectedWidgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(selectedWidgetWithTaskGroup.widget).isEqualToComparingFieldByField(widget)
        assertThat(selectedWidgetWithTaskGroup.singleTaskGroup).isEqualToComparingFieldByField(
            taskGroup
        )
    }
}