package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry

class TasksSection() :
    Section(SectionParameters.builder().itemResourceId(0).headerResourceId(0).build()) {
    var tasks: List<SingleTaskEntry> = listOf()

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
        return EmptyViewHolder(view)
    }

    inner class SingleTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }
}