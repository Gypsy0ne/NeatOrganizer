package one.gypsy.neatorganizer.presentation.common.listing

interface HeaderItem : ListedItem {
    val subItemsCount: Int
    val expanded: Boolean
}