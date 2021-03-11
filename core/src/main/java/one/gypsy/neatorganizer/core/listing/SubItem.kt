package one.gypsy.neatorganizer.core.listing

interface SubItem : Editable {
    val groupId: Long
    val done: Boolean
}
