package one.gypsy.neatorganizer.presentation.notes.model

import one.gypsy.neatorganizer.presentation.common.listing.Listed

data class NoteEntryItem(
    val createdAt: Long,
    val color: Int,
    override val title: String,
    override val id: Long
) : Listed
