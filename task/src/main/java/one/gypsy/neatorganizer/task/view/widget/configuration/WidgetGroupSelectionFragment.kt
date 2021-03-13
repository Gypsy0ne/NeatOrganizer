package one.gypsy.neatorganizer.task.view.widget.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.core.utils.extensions.showShortToast
import one.gypsy.neatorganizer.core.widget.WidgetConfigurationActivity
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_INVALID_ID
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.databinding.FragmentWidgetGroupSelectionBinding
import one.gypsy.neatorganizer.task.vm.TaskWidgetCreationStatus
import one.gypsy.neatorganizer.task.vm.TasksWidgetConfigurationViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class WidgetGroupSelectionFragment : Fragment() {

    private val widgetConfigurationViewModel: TasksWidgetConfigurationViewModel by viewModel()
    private lateinit var viewBinding: FragmentWidgetGroupSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_widget_group_selection,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        observeCreationStatus()
        viewBinding.bindViews()
    }

    private fun FragmentWidgetGroupSelectionBinding.bindViews() {
        configurationViewModel = widgetConfigurationViewModel
        lifecycleOwner = viewLifecycleOwner
        bindButtons()
        bindRecyclerView()
        executePendingBindings()
    }

    private fun FragmentWidgetGroupSelectionBinding.bindButtons() {
        cancelConfiguration.setOnClickListener {
            requireActivity().finish()
        }
        submitConfiguration.setOnClickListener {
            widgetConfigurationViewModel.onSubmitClicked(retrieveWidgetId())
        }
    }

    private fun retrieveWidgetId() =
        arguments?.getInt(TaskWidgetConfigureActivity.CONFIGURED_WIDGET_ID)
            ?: MANAGED_WIDGET_INVALID_ID

    private fun FragmentWidgetGroupSelectionBinding.bindRecyclerView() {
        tasksAdapter = TaskGroupEntriesAdapter(
            widgetConfigurationViewModel.selectedTaskGroup,
            { widgetConfigurationViewModel.onItemSelected(it) },
            { findNavController().navigate(R.id.addSingleTaskGroupDialogFragment) }
        )
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeCreationStatus() {
        widgetConfigurationViewModel.widgetCreationStatus.observe(viewLifecycleOwner) {
            when (it) {
                TaskWidgetCreationStatus.TaskNotSelectedStatus -> {
                    requireContext().showShortToast(resources.getString(R.string.task_widget_creation_task_warning))
                }
                TaskWidgetCreationStatus.ColorNotPickedStatus -> {
                    requireContext().showShortToast((resources.getString(R.string.widget_creation_color_warning)))
                }
                TaskWidgetCreationStatus.CreationSuccessStatus -> {
                    (requireActivity() as? WidgetConfigurationActivity)?.onWidgetCreationFinish()
                }
            }
        }
    }
}
