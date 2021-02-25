package one.gypsy.neatorganizer.routine.view

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.core.binding.setEditionEnabled
import one.gypsy.neatorganizer.core.listing.HeaderClickListener
import one.gypsy.neatorganizer.core.listing.ListedHeader
import one.gypsy.neatorganizer.core.utils.extensions.show
import one.gypsy.neatorganizer.core.utils.extensions.shrink
import one.gypsy.neatorganizer.routine.databinding.ItemRoutineHeaderBinding
import one.gypsy.neatorganizer.routine.model.RoutineListItem

internal class RoutineHeaderViewHolder(
    val itemBinding: ItemRoutineHeaderBinding,
    val clickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>
) : RoutineViewHolder(itemBinding.root), ListedHeader<RoutineListItem.RoutineListHeader> {

    override lateinit var viewData: RoutineListItem.RoutineListHeader

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListHeader)
        viewData = data

        updateEditable()
        setUpSwipeMenuBehavior()
        setUpAddListener()
        setUpExpanderListener()
        setUpEditListener()
        setUpEditionSubmitListener()
        setUpRemoveListener()

        itemBinding.apply {
            headerItem = data
            executePendingBindings()
        }
    }

    override fun updateEditable() {
        changeNameEditionAttributes()
        itemBinding.dayPickerItemRoutineHeaderSchedule.interactable = viewData.edited
        if (viewData.edited) {
            onEditStart()
        } else {
            onEditFinish()
        }
    }

    private fun onEditFinish() {
        itemBinding.buttonItemRoutineHeaderSubmit.shrink()
        itemBinding.buttonItemRoutineHeaderExpand.show()
    }

    private fun onEditStart() {
        itemBinding.buttonItemRoutineHeaderSubmit.show()
        itemBinding.buttonItemRoutineHeaderExpand.shrink()
    }

    private fun changeNameEditionAttributes() =
        setEditionEnabled(
            view = itemBinding.editTextItemRoutineHeaderName,
            editionEnabled = viewData.edited,
            requestEdit = true
        )

    override fun setUpSwipeMenuBehavior() =
        itemBinding.swipeLayoutItemRoutineHeaderRoot.setMenuSwipeListener(object :
                SwipeMenuListener {
                override fun onLeftMenuOpen() {
                    clearEditionStatus()
                }

                override fun onRightMenuOpen() {
                    clearEditionStatus()
                }
            })

    override fun clearEditionStatus() {
        viewData = viewData.copy(edited = false)
        updateEditable()
    }

    override fun setUpExpanderListener() =
        itemBinding.setExpanderClickListener {
            viewData = viewData.copy(expanded = !viewData.expanded)
            clickListener.onExpanderClick(viewData)
        }

    override fun setUpAddListener() =
        itemBinding.setAddClickListener {
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
            itemBinding.root.findNavController().navigateToAddRoutineTask(viewData.id)
        }

    private fun NavController.navigateToAddRoutineTask(routineId: Long) =
        navigate(RoutinesFragmentDirections.routinesToRoutineTaskAddition(routineId))

    override fun setUpEditListener() = with(itemBinding) {
        setEditClickListener {
            viewData = viewData.copy(edited = !viewData.edited)
            updateEditable()
            swipeLayoutItemRoutineHeaderRoot.resetStatus()
        }
    }

    override fun setUpEditionSubmitListener() = with(itemBinding) {
        setEditionSubmitClickListener {
            if (didItemContentChange()) {
                viewData = viewData.copy(
                    title = editTextItemRoutineHeaderName.text.toString(),
                    scheduleDays = dayPickerItemRoutineHeaderSchedule.scheduleDaysStatus
                )
                clickListener.onEditionSubmitClick(viewData)
            } else {
                clearEditionStatus()
            }
        }
    }

    private fun didItemContentChange() =
        viewData.title != itemBinding.editTextItemRoutineHeaderName.text.toString() ||
            viewData.scheduleDays != itemBinding.dayPickerItemRoutineHeaderSchedule.scheduleDaysStatus

    override fun setUpRemoveListener() = with(itemBinding) {
        setRemoveClickListener {
            swipeLayoutItemRoutineHeaderRoot.resetStatus()
            clickListener.onRemoveClick(viewData)
        }
    }
}
