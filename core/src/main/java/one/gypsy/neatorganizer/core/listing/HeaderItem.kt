package one.gypsy.neatorganizer.core.listing

interface HeaderItem : Listed, Editable {
    val subItemsCount: Int
    val expanded: Boolean
}
