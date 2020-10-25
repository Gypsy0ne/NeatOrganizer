package one.gypsy.neatorganizer.presentation.listing

interface HeaderClickListener<T : HeaderItem> {
    val onExpanderClick: (headerItem: T) -> Unit
    val onEditionSubmitClick: (headerItem: T) -> Unit
    val onRemoveClick: (headerItem: T) -> Unit
}