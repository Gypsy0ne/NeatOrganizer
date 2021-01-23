package one.gypsy.neatorganizer.presentation.notes.view.widget.management

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_task_widget.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ActivityNoteWidgetBinding
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetViewModel
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.MANAGED_NOTE_ID_KEY
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.MANAGED_NOTE_INVALID_ID
import one.gypsy.neatorganizer.presentation.tasks.view.widget.WidgetKeyring
import one.gypsy.neatorganizer.presentation.tasks.view.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteWidgetActivity : AppCompatActivity() {

    private val noteViewModel: NoteWidgetViewModel by viewModel {
        parametersOf(intent.getLongExtra(MANAGED_NOTE_ID_KEY, MANAGED_NOTE_INVALID_ID))
    }
    private lateinit var viewBinding: ActivityNoteWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpActionBar()
        setNavigationGraph()
        // TODO sync service
    }

    private fun setUpActionBar() {
        setSupportActionBar(manageToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setUpBinding() {
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_widget)
        viewBinding.apply {
            viewModel = noteViewModel
            lifecycleOwner = this@NoteWidgetActivity
            executePendingBindings()
        }
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
            intent.getIntExtra(MANAGED_WIDGET_ID_KEY, WidgetKeyring.MANAGED_WIDGET_INVALID_ID)
        )
    }
}
