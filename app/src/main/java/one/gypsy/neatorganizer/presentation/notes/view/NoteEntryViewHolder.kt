package one.gypsy.neatorganizer.presentation.notes.view

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.databinding.ItemNoteBinding
import one.gypsy.neatorganizer.presentation.notes.model.NoteEntryItem

class NoteEntryViewHolder(private val itemBinding: ItemNoteBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: NoteEntryItem) {

        itemBinding.noteItemContent.setOnClickListener {
            itemBinding.root.findNavController().navigateToNoteDetails(data.id)
        }
    }

    private fun NavController.navigateToNoteDetails(noteId: Long) =
        navigate(NotesFragmentDirections.notesToNoteDetails(noteId))
}
