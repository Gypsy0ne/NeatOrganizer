package one.gypsy.neatorganizer.note.view

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.note.databinding.ItemNoteBinding
import one.gypsy.neatorganizer.note.model.NoteEntryItem

internal class NoteEntryViewHolder(
    private val itemBinding: ItemNoteBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: NoteEntryItem) {
        itemBinding.noteEntryItem = data
        itemBinding.setOpenClickListener(data.id)
        itemBinding.setDeletionClickListener(data.id)
    }

    private fun ItemNoteBinding.setOpenClickListener(noteId: Long) =
        noteItemContent.setOnClickListener {
            itemBinding.root.findNavController().navigateToNoteDetails(noteId)
        }

    private fun ItemNoteBinding.setDeletionClickListener(noteId: Long) =
        noteDeletion.setOnClickListener {
            itemBinding.root.findNavController().navigateToNoteDeletion(noteId)
        }

    private fun NavController.navigateToNoteDetails(noteId: Long) =
        navigate(NotesFragmentDirections.notesToNoteDetails(noteId))

    private fun NavController.navigateToNoteDeletion(noteId: Long) =
        navigate(NotesFragmentDirections.notesToDeleteNoteConfirmation(noteId))
}
