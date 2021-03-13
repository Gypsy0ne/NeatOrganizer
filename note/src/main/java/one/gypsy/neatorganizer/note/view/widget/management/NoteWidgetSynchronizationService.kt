package one.gypsy.neatorganizer.note.view.widget.management

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import one.gypsy.neatorganizer.core.widget.WidgetNotifier
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntryDto
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntryDto
import one.gypsy.neatorganizer.domain.interactors.notes.GetAllNoteEntries
import one.gypsy.neatorganizer.domain.interactors.notes.widget.GetAllNoteWidgetIds
import one.gypsy.neatorganizer.domain.interactors.notes.widget.GetAllNoteWidgets
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.qualifier.named

class NoteWidgetSynchronizationService : LifecycleService(), KoinComponent {

    private val getAllWidgetIdsUseCase: GetAllNoteWidgetIds by inject()
    private val widgetNotifier: WidgetNotifier by inject(named("noteWidgetNotifier"))
    private val getAllNoteEntriesUseCase: GetAllNoteEntries = get()
    private val getAllNoteWidgetsUseCase: GetAllNoteWidgets = get()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        getAllNoteEntriesUseCase.invoke(lifecycleScope, Unit) {
            it.either(
                onFailure = { stopSelf() },
                onSuccess = ::onGetAllNotesSuccess
            )
        }
        getAllNoteWidgetsUseCase.invoke(lifecycleScope, Unit) {
            it.either(
                onFailure = { stopSelf() },
                onSuccess = ::onGetAllNoteWidgetsSuccess
            )
        }
        return START_STICKY
    }

    private fun onGetAllNotesSuccess(notes: LiveData<List<NoteEntryDto>>) =
        notes.observe(
            this,
            {
                getAllWidgetIdsUseCase.invoke(lifecycleScope, Unit) {
                    it.either(onSuccess = ::updateNoteWidgets)
                }
            }
        )

    private fun updateNoteWidgets(noteWidgetIds: IntArray) {
        widgetNotifier.sendUpdateWidgetBroadcast(noteWidgetIds)
    }

    private fun onGetAllNoteWidgetsSuccess(noteWidgets: LiveData<List<NoteWidgetEntryDto>>) {
        noteWidgets.observe(this) {
            updateNoteWidgets(it.map { widget -> widget.widgetId }.toIntArray())
        }
    }
}
