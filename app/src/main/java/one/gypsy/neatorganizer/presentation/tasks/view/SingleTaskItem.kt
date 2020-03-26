package one.gypsy.neatorganizer.presentation.tasks.view

import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import one.gypsy.neatorganizer.R
import com.xwray.groupie.kotlinandroidextensions.Item
import one.gypsy.neatorganizer.databinding.ItemSingleTaskBinding
import one.gypsy.neatorganizer.databinding.ItemSingleTaskGroupBinding
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry

class SingleTaskItem(val singleTask: SingleTaskEntry) : BindableItem<ItemSingleTaskBinding>() {
    var interactionListener: TasksFragment.SingleTaskInteractionListener? = null
    override fun getLayout(): Int {
        return R.layout.item_single_task
    }

    override fun bind(viewBinding: ItemSingleTaskBinding, position: Int) {
    }

}
