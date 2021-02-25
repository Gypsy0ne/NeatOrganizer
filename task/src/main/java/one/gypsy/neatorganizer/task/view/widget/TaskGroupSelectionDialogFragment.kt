package one.gypsy.neatorganizer.task.view.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.core.utils.extensions.showShortToast
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.databinding.DialogFragmentSelectTaskGroupBinding
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.SELECTED_WIDGET_GROUP_ID_KEY
import one.gypsy.neatorganizer.task.view.widget.configuration.TaskGroupEntriesAdapter
import one.gypsy.neatorganizer.task.vm.TaskWidgetSelectionStatus
import one.gypsy.neatorganizer.task.vm.TaskWidgetSelectionViewModel
import org.koin.android.viewmodel.ext.android.viewModel

internal class TaskGroupSelectionDialogFragment : BottomSheetDialogFragment() {

    private val args: TaskGroupSelectionDialogFragmentArgs by navArgs()
    private val selectionViewModel: TaskWidgetSelectionViewModel by viewModel()
    lateinit var fragmentBinding: DialogFragmentSelectTaskGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Transparent)
        isCancelable = false
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_select_task_group,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSelectionStatus()
        fragmentBinding.bindLayout()
    }

    private fun observeSelectionStatus() = selectionViewModel
        .widgetSelectionStatus
        .observe(this@TaskGroupSelectionDialogFragment) {
            when (it) {
                TaskWidgetSelectionStatus.TaskGroupNotSelectedStatus -> context?.showShortToast(
                    resources.getString(R.string.task_widget_creation_task_warning)
                )
                TaskWidgetSelectionStatus.SelectionSuccessStatus -> onSelectionSuccess(
                    selectionViewModel.selectedTaskGroup.value?.id
                )
            }
        }

    private fun DialogFragmentSelectTaskGroupBinding.bindLayout() {
        viewModel = selectionViewModel
        lifecycleOwner = this@TaskGroupSelectionDialogFragment
        bindViews()
    }

    private fun DialogFragmentSelectTaskGroupBinding.bindViews() {
        bindRecyclerView()
        bindButtons()
    }

    private fun DialogFragmentSelectTaskGroupBinding.bindRecyclerView() {
        tasksAdapter = TaskGroupEntriesAdapter(selectionViewModel.selectedTaskGroup) {
            selectionViewModel.onTaskGroupSelected(it)
        }
        layoutManager = LinearLayoutManager(context)
    }

    private fun DialogFragmentSelectTaskGroupBinding.bindButtons() {
        selectionCancelation.setOnClickListener { onCancelClicked() }
        selectionConfirmation.setOnClickListener { selectionViewModel.onSubmitClicked(args.widgetId) }
    }

    private fun setSelectionResult(selectedGroupId: Long?) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            SELECTED_WIDGET_GROUP_ID_KEY,
            selectedGroupId
        )
    }

    private fun onSelectionSuccess(selectedGroupId: Long?) {
        setSelectionResult(selectedGroupId)
        dismiss()
    }

    private fun onCancelClicked() {
        setSelectionResult(null)
        requireActivity().finish()
    }
}
