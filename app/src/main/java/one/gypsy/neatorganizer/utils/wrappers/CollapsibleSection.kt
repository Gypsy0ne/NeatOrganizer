package one.gypsy.neatorganizer.utils.wrappers

import io.github.luizgrp.sectionedrecyclerviewadapter.Section

interface CollapsibleSection {
    open var collapsed: Boolean
}

interface CollapseListener{
    fun onCollapse(section: Section)
}