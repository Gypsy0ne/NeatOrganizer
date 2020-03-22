package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.utils.wrappers.BindableSection
import one.gypsy.neatorganizer.utils.wrappers.CollapseListener
import one.gypsy.neatorganizer.utils.wrappers.CollapsibleSection

//TODO add item adding and removing
class SingleTaskGroupSection() : Section(
    SectionParameters.builder().itemResourceId(R.layout.item_single_task)
        .headerResourceId(R.layout.item_single_task_group).build()
), BindableSection<MutableList<SingleTaskEntry>>, CollapsibleSection {

    override var collapsed: Boolean = false
    lateinit var collapseListener: CollapseListener
    override var items: MutableList<SingleTaskEntry> = mutableListOf()
    set(value) {
        items.clear()
        items.addAll(value)
    }

    override fun getContentItemsTotal(): Int {
        return if(collapsed)
            0
        else
            items.size
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
        }
    }
    inner class SingleTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }

    }
    inner class TaskGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
        init {
            itemView.setOnClickListener {
                collapseListener.onCollapse(this@SingleTaskGroupSection)
            }
        }
    }

}