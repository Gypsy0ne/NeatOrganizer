package one.gypsy.neatorganizer.note.view.widget.configuration

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import one.gypsy.neatorganizer.core.widget.WidgetConfigurationActivity
import one.gypsy.neatorganizer.core.widget.WidgetRemoteViewManager
import one.gypsy.neatorganizer.note.R
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

internal class NoteWidgetConfigureActivity : WidgetConfigurationActivity() {

    override val widgetViewManager: WidgetRemoteViewManager by inject(named(NOTE_REMOTE_VIEW_MANAGER))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_widget_configuration)
        setNavigationGraphWithData()
    }

    private fun setNavigationGraphWithData() =
        (supportFragmentManager.findFragmentById(R.id.configurationContainer) as? NavHostFragment)
            ?.navController
            ?.setGraph(
                R.navigation.navigation_note_widget_configuration,
                createStartDataBundle()
            )

    private fun createStartDataBundle() = bundleOf(CONFIGURED_WIDGET_ID to appWidgetId)

    companion object {
        const val CONFIGURED_WIDGET_ID = "configuredWidgetId"
        const val NOTE_REMOTE_VIEW_MANAGER = "noteRemoteViewManager"
    }
}
