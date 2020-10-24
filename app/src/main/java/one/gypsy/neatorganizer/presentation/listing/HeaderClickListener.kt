package one.gypsy.neatorganizer.presentation.listing

interface HeaderClickListener<T : HeaderItem> {
    val onExpanderClick: (headerItem: T) -> Unit
    val onHeaderEditionSubmitClick: (headerItem: T) -> Unit
    val onHeaderRemoveClick: (headerItem: T) -> Unit

}