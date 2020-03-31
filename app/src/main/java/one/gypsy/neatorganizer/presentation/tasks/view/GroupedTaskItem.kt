package one.gypsy.neatorganizer.presentation.tasks.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import one.gypsy.neatorganizer.databinding.ItemGroupedTaskBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.GroupedTaskViewModel


class GroupedTaskItem(context: Context) : RelativeLayout(context) {
    lateinit var viewModel: GroupedTaskViewModel

    private val viewBinding: ItemGroupedTaskBinding =
        ItemGroupedTaskBinding.inflate(LayoutInflater.from(context), this,true)

}