package one.gypsy.neatorganizer.core.listing

interface SubItemClickListener<T : SubItem> {
    val onDoneClick: (subItem: T) -> Unit
    val onEditionSubmitClick: (subItem: T) -> Unit
    val onRemoveClick: (subItem: T) -> Unit
}
