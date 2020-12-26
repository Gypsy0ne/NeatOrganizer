package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.database.entity.Timestamped

class NoteEntry(val id: Long, val title: String, override val createdAt: Long) : Timestamped
