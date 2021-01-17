package one.gypsy.neatorganizer.presentation.notes.view.widget.configuration

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.WidgetNoteConfigurationBinding
import one.gypsy.neatorganizer.presentation.common.WidgetConfigurationActivity
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetConfigurationViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetCreationStatus
import one.gypsy.neatorganizer.utils.extensions.showShortToast
import org.koin.android.viewmodel.ext.android.viewModel

class NoteWidgetConfigureActivity : WidgetConfigurationActivity() {

    private val viewModel: NoteWidgetConfigurationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBoundContentView()
        observeCreationStatus()
    }

    private fun setDataBoundContentView() =
        DataBindingUtil.setContentView<WidgetNoteConfigurationBinding>(
            this,
            R.layout.widget_note_configuration
        ).also {
            it.bindViews()
        }

    private fun WidgetNoteConfigurationBinding.bindViews() {
        configurationViewModel = viewModel
        lifecycleOwner = this@NoteWidgetConfigureActivity
        bindButtons()
        bindRecyclerView()
        executePendingBindings()
    }

    private fun WidgetNoteConfigurationBinding.bindButtons() {
        cancelConfiguration.setOnClickListener {
            finish()
        }
        submitConfiguration.setOnClickListener {
            viewModel.saveNoteWidget(appWidgetId)
        }
    }

    private fun WidgetNoteConfigurationBinding.bindRecyclerView() {
        notesAdapter = WidgetNoteEntriesAdapter(viewModel.selectedNote) {
            viewModel.onNoteSelected(it)
        }
        layoutManager = GridLayoutManager(baseContext, GRID_SPAN_COUNT)
    }

    private fun observeCreationStatus() {
        viewModel.widgetCreationStatus.observe(this) {
            when (it) {
                NoteWidgetCreationStatus.NoteNotSelectedStatus -> {
                    baseContext.showShortToast(resources.getString(R.string.note_widget_creation_task_warning))
                }
                NoteWidgetCreationStatus.CreationSuccessStatus -> {
                    onWidgetCreationFinish()
                }
            }
        }
    }

    private companion object {
        const val GRID_SPAN_COUNT = 2
    }
}
