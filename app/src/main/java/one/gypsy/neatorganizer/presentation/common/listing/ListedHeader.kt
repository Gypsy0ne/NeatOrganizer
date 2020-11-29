package one.gypsy.neatorganizer.presentation.common.listing

interface ListedHeader<T : HeaderItem> : ListedView<T> {
    fun setUpExpanderListener()
    fun setUpAddListener()
}