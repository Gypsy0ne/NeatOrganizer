package one.gypsy.neatorganizer.presentation.common

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

abstract class WidgetConfigurationActivity : AppCompatActivity() {

    protected val widgetViewManager: WidgetRemoteViewManager by inject()
    protected var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findWidgetIdFromIntent()
        setActivityResult(RESULT_CANCELED)
        invalidateIntentWithWidgetId()
    }

    private fun findWidgetIdFromIntent() = intent.extras?.let {
        appWidgetId = it.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
        )
    }

    private fun invalidateIntentWithWidgetId() {
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
        }
    }

    private fun AppCompatActivity.setActivityResult(status: Int) {
        val result = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        setResult(status, result)
    }

    protected fun onWidgetCreationFinish() {
        widgetViewManager.updateWidget(appWidgetId)
        setActivityResult(RESULT_OK)
        finish()
    }
}
