package one.gypsy.neatorganizer.core.listing

interface HeaderItem : Editable {
    val subItemsCount: Int
    val expanded: Boolean
}
