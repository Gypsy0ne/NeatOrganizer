package one.gypsy.neatorganizer.presentation.notes.view.widget.management

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_task_widget.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ActivityNoteWidgetBinding
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.MANAGED_NOTE_ID_KEY
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.MANAGED_NOTE_INVALID_ID
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetKeyring

class NoteWidgetActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNoteWidgetBinding
    private lateinit var appBarMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setSupportActionBar(manageToolbar)
        setNavigationGraph()

    }

    private fun setUpBinding() {
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_widget)
        viewBinding.apply {
            //bind VM
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.widget_note_manage_menu, menu)
        appBarMenu = menu
        return true
    }

}