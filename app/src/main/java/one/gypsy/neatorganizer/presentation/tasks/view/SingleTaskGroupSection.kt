package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry

//TODO add item adding and removing
class SingleTaskGroupSection() : Section(SectionParameters.builder().itemResourceId(R.layout.item_single_task).headerResourceId(R.layout.item_single_task_group).build()), BindableAdapter<List<SingleTaskEntry>> {
    var tasks = mutableListOf<SingleTaskEntry>()


    override fun setData(dataCollection: List<SingleTaskEntry>) {
        tasks.clear()
        tasks.addAll(dataCollection)
    }

    override fun getContentItemsTotal(): Int {
        return tasks.size
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        //pass item ord position
        (holder as SingleTaskViewHolder).bind()
    }

    override fun getItemViewHolder(view: View?): RecyclerView.ViewHolder {
        return if (view != null) {
            SingleTaskViewHolder(view)
        } else {
            EmptyViewHolder(view)
        }
    }

    override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
        return if (view != null) {
            TaskGroupViewHolder(view)
        } else {
            EmptyViewHolder(view)
        }    }

    inner class SingleTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    inner class TaskGroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }


}