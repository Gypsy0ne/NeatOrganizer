package one.gypsy.neatorganizer.note.model

import one.gypsy.neatorganizer.core.listing.Listed

data class NoteEntryItem(
    val createdAt: Long,
    val color: Int,
    override val title: String,
    override val id: Long
) : Listed
