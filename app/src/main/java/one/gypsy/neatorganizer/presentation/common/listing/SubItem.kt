package one.gypsy.neatorganizer.presentation.common.listing

interface SubItem : Listed, Editable {
    val groupId: Long
    val done: Boolean
}
