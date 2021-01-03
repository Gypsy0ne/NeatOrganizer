package one.gypsy.neatorganizer.presentation.common.listing

interface HeaderItem : Listed, Editable {
    val subItemsCount: Int
    val expanded: Boolean
}
