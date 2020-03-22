package one.gypsy.neatorganizer.utils.extensions

import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import one.gypsy.neatorganizer.utils.wrappers.CollapsibleSection


fun SectionedRecyclerViewAdapter.collapseSection(section: Section) {
    if(section is CollapsibleSection) {
        this.getAdapterForSection(section).apply {
            notifyHeaderChanged()
            (section as CollapsibleSection).collapsed = true
            notifyItemRangeRemoved(0, section.contentItemsTotal)
        }
    }
}

fun SectionedRecyclerViewAdapter.expandSection(section: Section) {
    this.getAdapterForSection(section).apply {
        notifyHeaderChanged()
        (section as CollapsibleSection).collapsed = false
        notifyAllItemsInserted()
    }
}

