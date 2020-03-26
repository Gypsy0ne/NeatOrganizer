package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ItemSingleTaskBinding
import one.gypsy.neatorganizer.databinding.ItemSingleTaskGroupBinding
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup

class SingleTaskGroupItem(val singleTaskGroup: SingleTaskGroup) : BindableItem<ItemSingleTaskGroupBinding>(), ExpandableItem {

    private var expandableGroup: ExpandableGroup? = null
    var interactionListener: TasksFragment.TaskGroupInteractionListener? = null


    override fun getLayout(): Int {
        return R.layout.item_single_task_group
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }

    override fun bind(viewBinding: ItemSingleTaskGroupBinding, position: Int) {
        viewBinding.root.setOnClickListener {
                    expandableGroup?.isExpanded = true
        }
    }


    interface SingleTaskInteractionListener {
    }

}