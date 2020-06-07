package one.gypsy.neatorganizer.presentation.listing

interface HeaderItem : ListedItem {
    val subItemsCount: Int
    val expanded: Boolean
}