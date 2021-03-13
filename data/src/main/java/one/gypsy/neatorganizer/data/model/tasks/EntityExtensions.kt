package one.gypsy.neatorganizer.data.model.tasks

import one.gypsy.neatorganizer.database.entity.tasks.GroupWithSingleTasks
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity
import one.gypsy.neatorganizer.database.entity.tasks.WidgetAndTaskGroup

internal fun GroupWithSingleTasks.toSingleTaskGroupWithTasks() = SingleTaskGroupWithTasks(
    taskGroup = group.toSingleTaskGroup(),
    tasks = this.tasks.map { it.toSingleTaskEntry() },
)

internal fun GroupWithSingleTasks.toSingleTaskGroupEntry() = SingleTaskGroupEntry(
    group.id,
    group.name,
    tasksDone = tasks.count { it.done },
    tasksCount = tasks.count()
)

internal fun SingleTaskEntity.toSingleTaskEntry() =
    SingleTaskEntry(
        id = this.id,
        name = this.name,
        done = this.done,
        groupId = this.groupId,
        createdAt = this.createdAt
    )

internal fun SingleTaskGroupEntity.toSingleTaskGroup() =
    SingleTaskGroup(name, id = id, createdAt = this.createdAt)

internal fun TaskWidgetEntity.toTaskWidgetEntry() =
    TaskWidgetEntry(appWidgetId = widgetId, taskGroupId = taskGroupId, widgetColor = color)

internal fun WidgetAndTaskGroup.toTitledTaskWidgetEntry() = TitledTaskWidgetEntry(
    appWidgetId = this.widget.widgetId,
    taskGroupId = this.widget.taskGroupId,
    widgetColor = this.widget.color,
    taskGroupTitle = this.singleTaskGroup.name
)
