package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.presentation.notes.model.NoteEntryItem

class NoteWidgetSelectionViewModel : ViewModel() {

    private val _selectedNote = MutableLiveData<NoteEntryItem>()
    val selectedNote: LiveData<NoteEntryItem> = _selectedNote

    private val _widgetSelectionStatus = MutableLiveData<NoteWidgetSelectionStatus>()
    val widgetSelectionStatus: LiveData<NoteWidgetSelectionStatus> = _widgetSelectionStatus

    fun onSubmitClicked(widgetId: Int) = if (selectedNote.value == null) {
        _widgetSelectionStatus.postValue(
            NoteWidgetSelectionStatus.NoteNotSelectedStatus
        )
    } else {
//        submitWidgetCreation(widgetId)
    }

//    private fun submitWidgetCreation(widgetId: Int) =
//        selectedNote.value?.id?.let { groupId ->
//            widgetUpdateUseCase.invoke(
//                viewModelScope,
//                UpdateTaskWidgetLinkedGroup.Params(widgetId, groupId)
//            ) { it.either({}, ::onUpdateTaskWidgetSuccess) }
//        }
}

sealed class NoteWidgetSelectionStatus {
    object NoteNotSelectedStatus : NoteWidgetSelectionStatus()
    object SelectionSuccessStatus : NoteWidgetSelectionStatus()
}
