package one.gypsy.neatorganizer.di

import android.appwidget.AppWidgetManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val serviceModule = module {
    single { AppWidgetManager.getInstance(androidApplication()) }
}