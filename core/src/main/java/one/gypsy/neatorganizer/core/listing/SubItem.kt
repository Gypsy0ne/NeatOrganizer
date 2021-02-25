package one.gypsy.neatorganizer.core.listing

interface SubItem : Listed, Editable {
    val groupId: Long
    val done: Boolean
}
