package one.gypsy.neatorganizer.note.view.widget.management

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_note_widget.manageToolbar
import kotlinx.android.synthetic.main.activity_task_widget.*
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_INVALID_ID
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.ActivityNoteWidgetBinding
import one.gypsy.neatorganizer.note.view.widget.NoteWidgetKeyring.MANAGED_NOTE_ID_KEY
import one.gypsy.neatorganizer.note.view.widget.NoteWidgetKeyring.MANAGED_NOTE_INVALID_ID

class NoteWidgetActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNoteWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_widget)
        setUpActionBar()
        setNavigationGraph()
        startWidgetSynchronizationService()
    }

    private fun setUpActionBar() {
        setSupportActionBar(manageToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setNavigationGraph() = findNavController(R.id.navigationFragmentsContainer)
        .setGraph(R.navigation.navigation_note_widget, createStartDestinationBundle())

    private fun createStartDestinationBundle() = Bundle().apply {
        putLong(
            MANAGED_NOTE_ID_KEY,
            intent.getLongExtra(
                MANAGED_NOTE_ID_KEY,
                MANAGED_NOTE_INVALID_ID
            )
        )
        putInt(
            MANAGED_WIDGET_ID_KEY,
            intent.getIntExtra(MANAGED_WIDGET_ID_KEY, MANAGED_WIDGET_INVALID_ID)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, NoteWidgetSynchronizationService::class.java))
    }

    private fun startWidgetSynchronizationService() =
        Intent(this, NoteWidgetSynchronizationService::class.java).also { intent ->
            startService(intent)
        }
}
