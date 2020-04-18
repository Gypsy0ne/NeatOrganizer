package one.gypsy.neatorganizer.utils

import androidx.recyclerview.widget.RecyclerView

sealed class UIState {
    object Loading: UIState()
    object Empty: UIState()
    object Success: UIState()
}

sealed class CollectionUIState(var itemPosition: Int): UIState() {
    class ItemEditionSuccess(itemPosition: Int): CollectionUIState(itemPosition)
    class ItemEditionFailure(itemPosition: Int): CollectionUIState(itemPosition)
}