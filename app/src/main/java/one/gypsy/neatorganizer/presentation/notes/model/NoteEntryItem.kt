package one.gypsy.neatorganizer.presentation.notes.model

import one.gypsy.neatorganizer.presentation.common.listing.Listed

data class NoteEntryItem(
    override val id: Long,
    val createdAt: Long,
    override val title: String,
) : Listed
