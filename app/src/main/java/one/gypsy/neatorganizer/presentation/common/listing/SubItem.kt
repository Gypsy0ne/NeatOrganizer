package one.gypsy.neatorganizer.presentation.common.listing

interface SubItem : ListedItem {
    val groupId: Long
    val done: Boolean
}