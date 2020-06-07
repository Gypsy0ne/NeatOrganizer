package one.gypsy.neatorganizer.presentation.listing

interface SubItem : ListedItem {
    val groupId: Long
    val done: Boolean
}