package one.gypsy.neatorganizer.presentation.notes.view.widget.management

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.notes.GetAllNoteEntries
import one.gypsy.neatorganizer.domain.interactors.notes.widget.GetAllNoteWidgetIds
import one.gypsy.neatorganizer.domain.interactors.notes.widget.GetAllNoteWidgets
import one.gypsy.neatorganizer.presentation.common.WidgetNotifier
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
                {
                    stopSelf()
                },
                ::onGetAllNotesSuccess
            )
        }
        getAllNoteWidgetsUseCase.invoke(lifecycleScope, Unit) {
            it.either(
                {
                    stopSelf()
                },
                ::onGetAllNoteWidgetsSuccess
            )
        }
        return START_STICKY
    }

    private fun onGetAllNotesSuccess(notes: LiveData<List<NoteEntry>>) =
        notes.observe(
            this,
            {
                getAllWidgetIdsUseCase.invoke(lifecycleScope, Unit) {
                    it.either({}, ::updateNoteWidgets)
                }
            }
        )

    private fun updateNoteWidgets(noteWidgetIds: IntArray) {
        widgetNotifier.sendUpdateWidgetBroadcast(noteWidgetIds)
    }

    private fun onGetAllNoteWidgetsSuccess(noteWidgets: LiveData<List<NoteWidgetEntry>>) {
        noteWidgets.observe(this) {
            updateNoteWidgets(it.map { widget -> widget.widgetId }.toIntArray())
        }
    }
}
