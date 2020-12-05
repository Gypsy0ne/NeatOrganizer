package one.gypsy.neatorganizer.presentation.common.listing

interface ListedSubItem<T : SubItem> : ListedView<T> {
    fun setUpDoneListener()
}
